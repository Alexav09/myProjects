package Principal;

import com.google.gson.Gson;

public class Teste {
    public static void main(String[] args) {
    	
    	
    	
        String cep = "01001000";
        String json = ConsultaCEP.buscarCep(cep);
        System.out.println(json);

        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(json, Endereco.class);

        // Agora você pode acessar os dados do JSON através do objeto endereco
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Complemento: " + endereco.getComplemento());
        System.out.println("Unidade: " + endereco.getUnidade());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Localidade: " + endereco.getLocalidade());
        System.out.println("UF: " + endereco.getUf());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println("Região: " + endereco.getRegiao());
        System.out.println("IBGE: " + endereco.getIbge());
        System.out.println("GIA: " + endereco.getGia());
        System.out.println("DDD: " + endereco.getDdd());
        System.out.println("SIAFI: " + endereco.getSiafi());
    }
}
