package cloud.nextsol.core.enums;

import cloud.nextsol.core.common.interfaces.StatusCodeItf;
import io.grpc.Status;
import org.springframework.http.HttpStatus;

public enum ResponseStatus implements StatusCodeItf {
    SOMETHING_WENT_WRONG(Status.INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR, "SOMETHING_WENT_WRONG"),
    ;

    private String errorCode;
    private Status gRPCStatus;
    private HttpStatus httpStatus;

    ResponseStatus(Status gRPCStatus, HttpStatus httpStatus, String errorCode) {
        this.gRPCStatus = gRPCStatus;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    @Override
    public Status getGRPCStatus() {
        return this.gRPCStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}
