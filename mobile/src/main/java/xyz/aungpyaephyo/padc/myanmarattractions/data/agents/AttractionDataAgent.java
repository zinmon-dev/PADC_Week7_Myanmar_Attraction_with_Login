package xyz.aungpyaephyo.padc.myanmarattractions.data.agents;

/**
 * Created by aung on 7/9/16.
 */
public interface AttractionDataAgent {
    void loadAttractions();
    void register(String name,String email,String password,String dateofBirth,String countryofOrigin);
    void login(String email,String password);
}
