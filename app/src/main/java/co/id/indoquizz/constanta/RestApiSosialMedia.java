package co.id.indoquizz.constanta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RestApiSosialMedia {
    String getUserDetail = "user/ig_data/{username}";
    String getInfluencer = "api/influencer";
    String login = "api/user/login";
    String getUser = "api/user";
    String getUserById = "api/influencer/{influencer_id}";
    String dataIg = "api/influencer/data/{platform}/{username}";
    String submitOrder = "api/order";
    String getOrder = "api/order";
    String getSingleOrder = "api/order/{order_id}";
    String updateUser = "api/user/{user_id}";
    String checkOrder = "api/order/status";
}