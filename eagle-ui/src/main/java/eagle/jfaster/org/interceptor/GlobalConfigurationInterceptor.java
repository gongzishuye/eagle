package eagle.jfaster.org.interceptor;

import com.google.common.base.Optional;
import eagle.jfaster.org.pojo.RegistryCenterConfiguration;
import eagle.jfaster.org.factory.RegistryCenterFactory;
import eagle.jfaster.org.service.RegistryCenterConfigurationService;
import eagle.jfaster.org.service.impl.RegistryCenterConfigurationServiceImpl;
import eagle.jfaster.org.util.SessionRegistryCenterConfiguration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static eagle.jfaster.org.controller.RegistryCenterRestfulApi.REG_CENTER_CONFIG_KEY;

/**
 * Created by fangyanpeng on 2017/8/24.
 */
public class GlobalConfigurationInterceptor extends HandlerInterceptorAdapter {

    private final RegistryCenterConfigurationService regCenterService = new RegistryCenterConfigurationServiceImpl();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if (null == httpSession.getAttribute(REG_CENTER_CONFIG_KEY)) {
            loadActivatedRegCenter(httpSession);
        }
        return true;
    }

    private void loadActivatedRegCenter(final HttpSession httpSession) {
        Optional<RegistryCenterConfiguration> config = regCenterService.loadActivated();
        if (config.isPresent()) {
            String configName = config.get().getName();
            boolean isConnected = setRegistryCenterNameToSession(regCenterService.find(configName, regCenterService.loadAll()), httpSession);
            if (isConnected) {
                regCenterService.load(configName);
            }
        }
    }

    private boolean setRegistryCenterNameToSession(final RegistryCenterConfiguration regCenterConfig, final HttpSession session) {
        session.setAttribute(REG_CENTER_CONFIG_KEY, regCenterConfig);
        try {
            RegistryCenterFactory.createCoordinatorRegistryCenter(regCenterConfig.getZkAddressList(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
            SessionRegistryCenterConfiguration.setRegistryCenterConfiguration((RegistryCenterConfiguration) session.getAttribute(REG_CENTER_CONFIG_KEY));
        } catch (final Exception ex) {
            return false;
        }
        return true;
    }
}
