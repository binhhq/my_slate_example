package cloud.nextsol.core.service;

import cloud.nextsol.core.client.UserClient;
import cloud.nextsol.core.common.error.BusinessLogicError;
import cloud.nextsol.core.common.pojo.GRPCClientResponseObject;
import cloud.nextsol.core.enums.ResponseStatus;
import cloud.nextsol.core.user.UserOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author quangtm at 01/09/2020
 */
@Service
public class GreeterServcice {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserClient userClient;

    public GreeterServcice(UserClient userClient) {
        this.userClient = userClient;
    }

    public String hello(String name) {
        if (name.equals("error")) {
            throw new BusinessLogicError(ResponseStatus.SOMETHING_WENT_WRONG, "LOI ROI BA CON OI");
        }
        GRPCClientResponseObject<UserOuterClass.User> user = userClient.getUserById(name);
        logger.info("user: {}", user.toString());
        return "Hello " + user.getData().getName();
    }
}
