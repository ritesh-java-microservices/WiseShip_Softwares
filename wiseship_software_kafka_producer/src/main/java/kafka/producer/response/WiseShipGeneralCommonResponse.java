package kafka.producer.response;

import java.net.http.HttpResponse;

public class WiseShipGeneralCommonResponse {

    private String responseBodyString;

    private String responseDataString;

    private Class<?> responseEntityClazz;

    private HttpResponse<WiseShipGeneralResponseDTO> httpResponse;

    private int responseCode;

    private String responseMessage;

}
