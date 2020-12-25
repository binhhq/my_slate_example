package cloud.nextsol.core.client;

import cloud.nextsol.core.common.pojo.GRPCClientResponseObject;
import cloud.nextsol.core.user.UserOuterClass;
import cloud.nextsol.core.user.UserServiceGrpc;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserClient(String target) {
        this(ManagedChannelBuilder.forTarget(target).usePlaintext().disableRetry());
    }

    public UserClient(ManagedChannelBuilder<?> channelBuilder) {
        Channel channel = channelBuilder.build();
        this.blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public GRPCClientResponseObject<UserOuterClass.User> getUserById(String id) {
        try {
            UserOuterClass.User user = blockingStub.getById(UserOuterClass.GetByIdRequest.newBuilder().setId(id).build());
            GRPCClientResponseObject<UserOuterClass.User> response = GRPCClientResponseObject.success();
            return response.setStatus(Status.OK).setData(user);
        } catch (StatusRuntimeException e) {
            logger.error("Error call to User.getById ", e);
            GRPCClientResponseObject<UserOuterClass.User> response = GRPCClientResponseObject.failed();
            return response.setStatus(e.getStatus())
                    .setMetadata(e.getTrailers());
        }
    }

}
