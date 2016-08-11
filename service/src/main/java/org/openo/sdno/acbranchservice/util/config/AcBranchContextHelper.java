package org.openo.sdno.acbranchservice.util.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AcBranchContextHelper implements ApplicationContextAware{
	private static ApplicationContext appCtx;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        appCtx = arg0;
    }

    /**
     * Get bean object.<br/>
     * 
     * @param beanName bean name
     * @return bean object
     * @since SDNO 0.5
     */
    public static Object getBean(String beanName) {
        if(null != appCtx) {
            return appCtx.getBean(beanName);
        }
        return null;
    }

}
