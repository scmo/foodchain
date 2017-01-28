package foodchain;

/**
 * Created by draft on 28.01.17.
 */
public class CLI1 {

    public static final String HOME ="/home/draft/git/foodchain/smartcontract/";

    public static void main(String[] args) {
        try {
            org.web3j.codegen.SolidityFunctionWrapperGenerator.main(new String[]{HOME +"src/main/sol/FoodChain.bin",
                    HOME+"src/main/sol/FoodChain.abi", "-o", HOME+"src/main/java", "-p", "foodchain"});
        } catch (Throwable t)
        {
            t.printStackTrace();;
        }
    }
}
