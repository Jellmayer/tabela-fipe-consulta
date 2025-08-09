package com.jellmayer.consulta_tabela_fipe;

import com.jellmayer.consulta_tabela_fipe.service.ApiClient;
import com.jellmayer.consulta_tabela_fipe.service.ResponseMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private final String BASE_URL = "https://fipe.parallelum.com.br/api/v2/";
    private static final ApiClient client = new ApiClient();
    private static final ResponseMapper mapper = new ResponseMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public void showMenu() throws IOException, InterruptedException {
        Map<String, Integer> foundBrands = searchBrandsByVehicle();
        int selectedBrandId = selectBrand(foundBrands);
    }

    public Map<String, Integer> searchBrandsByVehicle() throws IOException, InterruptedException {
        var vehicleType = "";

        loopVehicleType: while (true){
            System.out.println("**** OPÇÕES ****" +
                    "\n1. Carro" +
                    "\n2. Moto" +
                    "\n3. Caminhão");
            System.out.println("\nDeseja consultar qual tipo de veículo? ");
            var option = scanner.nextLine().trim();

            if (option.equals("1") || option.equalsIgnoreCase("carro")) {
                vehicleType = "cars";
                break loopVehicleType;
            } else if (option.equals("2") || option.equalsIgnoreCase("moto")) {
                vehicleType = "motorcycles";
                break loopVehicleType;
            } else if (option.equals("3") || option.equalsIgnoreCase("caminhao") || option.equalsIgnoreCase("caminhão")) {
                vehicleType = "trucks";
                break loopVehicleType;
            }
        }

        String url = BASE_URL + vehicleType + "/brands/";
        String jsonResponse = client.fetchData(url);
        return mapper.mapBrands(jsonResponse);
    }

    public Integer selectBrand(Map<String, Integer> brandsMap){
        System.out.println("Digite um trecho do nome da marca desejada: ");
        var brandSearchTerm = scanner.nextLine();

        System.out.println("======= MARCAS ENCONTRADAS =======");
        brandsMap.entrySet().stream()
                .filter(b -> b.getKey().toLowerCase().contains(brandSearchTerm.toLowerCase()))
                .forEach(entry -> {
                    System.out.printf("| %d | %-20s |", entry.getValue(), entry.getKey());
                });
        System.out.println("Digite o código da marca desejada:");
        return scanner.nextInt(); // Retorna o ID da marca selecionada
    }
}
