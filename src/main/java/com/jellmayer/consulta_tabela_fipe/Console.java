package com.jellmayer.consulta_tabela_fipe;

import com.jellmayer.consulta_tabela_fipe.model.ModelYearData;
import com.jellmayer.consulta_tabela_fipe.model.VehicleModel;
import com.jellmayer.consulta_tabela_fipe.service.ApiClient;
import com.jellmayer.consulta_tabela_fipe.service.ResponseMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Console {
    private static final String BASE_URL = "https://fipe.parallelum.com.br/api/v2/";
    private static final ApiClient client = new ApiClient();
    private static final ResponseMapper mapper = new ResponseMapper();
    private static final Scanner scanner = new Scanner(System.in);

    private String url;

    public void showMenu() throws IOException, InterruptedException {
        url = BASE_URL;
        Map<String, String> foundBrands = searchBrandsByVehicle();
        int selectedBrandId = selectBrand(foundBrands);
        int selectedModelId = selectModel(selectedBrandId);
        VehicleModel selectedModel = searchModel(selectedModelId);

    }

    public Map<String, String> searchBrandsByVehicle() throws IOException, InterruptedException {
        var vehicleType = "";

        loopVehicleType: while (true){
            System.out.println("\n**** OPÇÕES ****" +
                    "\n1. Carro" +
                    "\n2. Moto" +
                    "\n3. Caminhão");
            System.out.println("\nDeseja consultar qual tipo de veículo? ");
            var option = scanner.nextLine().trim();

            if (option.equals("1") || option.equalsIgnoreCase("carro")) {
                System.out.println("\nConultando marcas de carro...");
                vehicleType = "cars";
                break loopVehicleType;
            } else if (option.equals("2") || option.equalsIgnoreCase("moto")) {
                System.out.println("\nConultando marcas de moto...");
                vehicleType = "motorcycles";
                break loopVehicleType;
            } else if (option.equals("3") || option.equalsIgnoreCase("caminhao") || option.equalsIgnoreCase("caminhão")) {
                System.out.println("\nConultando marcas de caminhão...");
                vehicleType = "trucks";
                break loopVehicleType;
            }
        }

        url += vehicleType + "/brands/";
        String jsonResponse = client.fetchData(url);
        return mapper.mapData(jsonResponse);
    }

    public Integer selectBrand(Map<String, String> brandsMap){
        System.out.println("Digite um trecho do nome da marca desejada: ");
        var brandSearchTerm = scanner.nextLine();

        System.out.println("\n======= MARCAS ENCONTRADAS =======");
        brandsMap.entrySet().stream()
                .filter(b -> b.getValue().toLowerCase().contains(brandSearchTerm.toLowerCase()))
                .sorted()
                .forEach(entry -> {
                    System.out.printf("| %3s | %-24s |", entry.getKey(), entry.getValue());
                });
        System.out.println("\nDigite o código da marca desejada:");
        int brandId =  scanner.nextInt();
        scanner.nextLine();
        return brandId; // Retorna o ID da marca selecionada
    }

    public Integer selectModel(Integer brandCode) throws IOException, InterruptedException {
        url += brandCode + "/models/";
        String jsonResponse = client.fetchData(url);
        Map<String, String> modelsMap = mapper.mapData(jsonResponse);

        System.out.println("\n======= LINHAS DA MARCA =======");
        modelsMap.values().stream()
                .map(s -> s.contains(" ") ? s.substring(0, s.indexOf(' ')) : s)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println("Digite o nome da linha desejada:");
        var selectedModelLine = scanner.nextLine();

        System.out.println("\n=============== Modelos da linha " + selectedModelLine + " ===============");
        modelsMap.entrySet().stream()
                .filter(entry -> entry.getValue().contains(selectedModelLine))
                .forEach(entry -> {
                    System.out.printf("| %-5s | %-45s |\n", entry.getKey(), entry.getValue());
                });

        System.out.println("Digite o código do modelo desejado:");
        int modelId = scanner.nextInt();
        scanner.nextLine();
        return modelId; // Retorna o ID do modelo desejado
    }

    public VehicleModel searchModel(Integer modelId) throws IOException, InterruptedException {
        url += modelId + "/years/";

        VehicleModel vehicleModel = new VehicleModel();

        String jsonResponse = client.fetchData(url);
        Set<String> years = mapper.mapData(jsonResponse).keySet();
        years.forEach(yearCode -> {
            try {
                String json = client.fetchData((url + yearCode));
                ModelYearData yearData = mapper.parseJson(json, ModelYearData.class);
                System.out.println(yearData.modelYear() + " - " + yearData.price());
                vehicleModel.fetchData(yearData);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return vehicleModel;
    }
}
