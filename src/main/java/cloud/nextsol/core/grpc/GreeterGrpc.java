package cloud.nextsol.core.grpc;

import cloud.nextsol.core.common.error.BusinessLogicError;
import cloud.nextsol.core.common.utils.GRPCUtils;
import cloud.nextsol.greeter.GreeterOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import cloud.nextsol.core.service.GreeterServcice;

/**
 * @author quangtm at 01/09/2020
 */
@GRpcService
public class GreeterGrpc extends cloud.nextsol.greeter.GreeterGrpc.GreeterImplBase {

    private GreeterServcice greeterServcice;

    public GreeterGrpc(GreeterServcice greeterServcice) {
        this.greeterServcice = greeterServcice;
    }

    @Override
    public void hello(GreeterOuterClass.Text request, StreamObserver<GreeterOuterClass.Text> responseObserver) {
        try {
            String response = greeterServcice.hello(request.getMessage());
            responseObserver.onNext(GreeterOuterClass.Text.newBuilder().setMessage(response).build());
            responseObserver.onCompleted();
        } catch (BusinessLogicError error) {
            responseObserver.onError(GRPCUtils.createStatusRuntimeException(error));
        }
    }

}
