package xyz.aungpyaephyo.padc.myanmarattractions.data.agents.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.aungpyaephyo.padc.myanmarattractions.data.responses.AttractionListResponse;
import xyz.aungpyaephyo.padc.myanmarattractions.data.responses.RegisterResponse;
import xyz.aungpyaephyo.padc.myanmarattractions.utils.MyanmarAttractionsConstants;

/**
 * Created by aung on 7/9/16.
 */
public interface AttractionApi {

    @FormUrlEncoded
    @POST(MyanmarAttractionsConstants.API_GET_ATTRACTION_LIST)
    Call<AttractionListResponse> loadAttractions(
            @Field(MyanmarAttractionsConstants.PARAM_ACCESS_TOKEN) String param);

    //get data with form data
    @FormUrlEncoded
    @POST(MyanmarAttractionsConstants.API_REGISTER)
    Call<RegisterResponse> register(
            @Field(MyanmarAttractionsConstants.PARAM_NAME)String name,
            @Field(MyanmarAttractionsConstants.PARAM_EMAIL)String email,
            @Field(MyanmarAttractionsConstants.PARAM_PASSWORD)String password,
            @Field(MyanmarAttractionsConstants.PARAM_DATE_OF_BIRTH)String dateofBirth,
            @Field(MyanmarAttractionsConstants.PARAM_COUNTRY_OF_ORIGIN)String countryofOrigin);

    @FormUrlEncoded
    @POST(MyanmarAttractionsConstants.API_LOGIN)
    Call<RegisterResponse> login(
            @Field(MyanmarAttractionsConstants.PARAM_EMAIL)String email,
            @Field(MyanmarAttractionsConstants.PARAM_PASSWORD)String password );

}
