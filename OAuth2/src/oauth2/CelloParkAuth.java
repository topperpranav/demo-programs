package oauth2;

import com.google.gson.annotations.SerializedName;

/**
 * Class to serialize/deserialize the authorization header
 *
 * @author Pranav
 */
public class CelloParkAuth {

    @SerializedName("access_token")
    String accessToken;
    @SerializedName("token_type")
    String tokenType;
    @SerializedName("expires_in")
    int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
