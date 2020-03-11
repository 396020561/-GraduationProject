//package com.nchu.config;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import com.nchu.realm.UserRealm;
//
////@Configuration
//public class ShiroConfiguration {
//
//	@Bean
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		// 设置安全管理器
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//		// 默认跳转到登陆页面
//		shiroFilterFactoryBean.setLoginUrl("/login");
//		// 登陆成功后的页面
//		shiroFilterFactoryBean.setSuccessUrl("/index");
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//
//		// 自定义过滤器
//		Map<String, Filter> filterMap = new LinkedHashMap<>();
//		shiroFilterFactoryBean.setFilters(filterMap);
//		// 权限控制map
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//		// 配置不会被拦截的链接 顺序判断
//		filterChainDefinitionMap.put("/static/**", "anon");
//		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//		filterChainDefinitionMap.put("/logout", "logout");
//		// //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//		// //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//		// filterChainDefinitionMap.put("/**", "anon");
//
//		// 登录请求
//		filterChainDefinitionMap.put("/toLogin", "anon");
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		return shiroFilterFactoryBean;
//	}
//	
//	/**
//	 * 身份认证Realm，此处的注入不可以缺少。否则会在UserRealm中注入对象会报空指针.
//	 * 
//	 * @return
//	 */
//	@Bean(name = "userRealm")
//	public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher) {
//		UserRealm myShiroRealm = new UserRealm();
//		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//		return myShiroRealm;
//	}
//
//	/**
//	 * 核心的安全事务管理器
//	 * @param <MyShiroRealm>
//	 * 
//	 * @return
//	 */
//	@Bean(name="securityManager")
//	public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		// 设置realm
//		securityManager.setRealm(userRealm);
//		securityManager.setRememberMeManager(rememberMeManager());
//		return securityManager;
//	}
//
//
//
//	/**
//	 * 哈希密码比较器。在myShiroRealm中作用参数使用 登陆时会比较用户输入的密码，跟数据库密码配合盐值salt解密后是否一致。
//	 * 
//	 * @return
//	 */
//	@Bean(name="hashedCredentialsMatcher")
//	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用md5算法;
//		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(
//														// md5(""));
//		return hashedCredentialsMatcher;
//	}
//
//	// //注入缓存
//	// @Bean
//	// public EhCacheManager ehCacheManager(){
//	// System.out.println("ShiroConfiguration.getEhCacheManager()执行");
//	// EhCacheManager cacheManager=new EhCacheManager();
//	// cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//	// return cacheManager;
//	// }
//
//	/**
//	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;否则@RequiresRoles等注解无法生效
//	 * 
//	 * @param securityManager
//	 * @return
//	 */
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//
//	/**
//	 * Shiro生命周期处理器
//	 * 
//	 * @return
//	 */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//
//	/**
//	 * 自动创建代理
//	 * 
//	 * @return
//	 */
//	@Bean
//	@DependsOn({ "lifecycleBeanPostProcessor" })
//	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		advisorAutoProxyCreator.setProxyTargetClass(true);
//		return advisorAutoProxyCreator;
//	}
//
//	@Bean
//	public SimpleCookie rememberMeCookie() {
//		// System.out.println("ShiroConfiguration.rememberMeCookie()");
//		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
//		simpleCookie.setMaxAge(259200);
//		return simpleCookie;
//	}
//
//	/**
//	 * cookie管理对象;
//	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
//	 * 
//	 * @return
//	 */
//	@Bean
//	public CookieRememberMeManager rememberMeManager() {
//		// System.out.println("ShiroConfiguration.rememberMeManager()");
//		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//		cookieRememberMeManager.setCookie(rememberMeCookie());
//		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
//		return cookieRememberMeManager;
//	}
//}
