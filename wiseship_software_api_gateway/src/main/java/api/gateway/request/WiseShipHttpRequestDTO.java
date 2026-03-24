package api.gateway.request;

import api.gateway.dtos.WiseShipGeneralUniversalDTO;
import io.netty.util.internal.StringUtil;

import java.util.Date;

public class WiseShipHttpRequestDTO extends WiseShipCommonBaseRequest {

    private String requestDataString;

    private Class<?> requestEntityClazz;

    private  WiseShipGeneralUniversalDTO requestDto =
            new WiseShipGeneralUniversalDTO();

    public WiseShipHttpRequestDTO() {
        super();
        requestDto = new WiseShipGeneralUniversalDTO();
        this.initializeRequest(requestDto);
    }

    public void initializeRequest(WiseShipGeneralUniversalDTO httpRequestDto) {
        requestDto.initializeDateDTO(new Date(), false, false, false);
        requestDto.initializeStringDTO(StringUtil.EMPTY_STRING, false, false, false);
        requestDto.initializeObjectDTO(StringUtil.EMPTY_STRING, false, false, false);
        requestDto.initializeLongDTO(0L, false, false, false);
        requestDto.initializeDoubleDTO(0.0d, false, false, false);
    }


}
