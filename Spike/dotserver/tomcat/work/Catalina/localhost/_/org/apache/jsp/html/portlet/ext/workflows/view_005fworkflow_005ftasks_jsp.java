package org.apache.jsp.html.portlet.ext.workflows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.dotmarketing.business.RoleAPI;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.business.Layout;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.auth.PrincipalException;
import com.liferay.portal.ejb.AddressManagerUtil;
import com.liferay.portal.ejb.CompanyLocalManagerUtil;
import com.liferay.portal.ejb.PortletManagerUtil;
import com.liferay.portal.ejb.PortletPreferencesManagerUtil;
import com.liferay.portal.ejb.UserLocalManagerUtil;
import com.liferay.portal.model.*;
import com.liferay.portal.util.Constants;
import com.liferay.portal.util.CookieKeys;
import com.liferay.portal.util.ImageKey;
import com.liferay.portal.util.LuceneFields;
import com.liferay.portal.util.OmniadminUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.Recipient;
import com.liferay.portal.util.RecipientComparator;
import com.liferay.portal.util.ReleaseInfo;
import com.liferay.portal.util.Resolution;
import com.liferay.portal.util.ShutdownUtil;
import com.liferay.portal.util.WebAppPool;
import com.liferay.portlet.CachePortlet;
import com.liferay.portlet.LiferayWindowState;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.RenderParametersPool;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.portlet.RenderResponseImpl;
import com.liferay.portlet.admin.ejb.AdminConfigManagerUtil;
import com.liferay.portlet.admin.model.EmailConfig;
import com.liferay.portlet.admin.model.JournalConfig;
import com.liferay.portlet.admin.model.ShoppingConfig;
import com.liferay.portlet.admin.model.UserConfig;
import com.liferay.util.BrowserSniffer;
import com.liferay.util.CollectionFactory;
import com.liferay.util.CookieUtil;
import com.liferay.util.CreditCard;
import com.liferay.util.FileUtil;
import com.liferay.util.Html;
import com.liferay.util.Http;
import com.liferay.util.JS;
import com.liferay.util.KeyValuePair;
import com.liferay.util.KeyValuePairComparator;
import com.liferay.util.MathUtil;
import com.liferay.util.ObjectValuePair;
import com.liferay.util.OrderedProperties;
import com.liferay.util.ParamUtil;
import com.liferay.util.PhoneNumber;
import com.liferay.util.PropertiesUtil;
import com.liferay.util.ServerDetector;
import com.liferay.util.SimpleCachePool;
import com.liferay.util.SortedProperties;
import com.liferay.util.State;
import com.liferay.util.StateUtil;
import com.liferay.util.StringComparator;
import com.liferay.util.StringPool;
import com.liferay.util.TextFormatter;
import com.liferay.util.Time;
import com.liferay.util.UnicodeFormatter;
import com.liferay.util.Validator;
import com.liferay.util.Xss;
import com.liferay.util.cal.CalendarUtil;
import com.liferay.util.cal.Recurrence;
import com.liferay.util.lang.BooleanWrapper;
import com.liferay.util.lang.IntegerWrapper;
import com.liferay.util.log4j.Levels;
import com.liferay.util.lucene.Hits;
import com.liferay.util.servlet.DynamicServletRequest;
import com.liferay.util.servlet.SessionParameters;
import com.liferay.util.servlet.StringServletResponse;
import com.liferay.util.servlet.UploadException;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.UnavailableException;
import javax.portlet.ValidatorException;
import javax.portlet.WindowState;
import com.dotmarketing.portlets.common.bean.CrumbTrailEntry;
import java.util.HashMap;
import com.liferay.portal.language.LanguageUtil;
import com.liferay.portal.language.LanguageWrapper;
import com.liferay.portal.language.UnicodeLanguageUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.util.GetterUtil;
import com.liferay.util.StringUtil;
import com.liferay.util.servlet.SessionErrors;
import com.liferay.util.servlet.SessionMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.dotcms.publisher.environment.bean.Environment;
import com.dotcms.publisher.bundle.bean.Bundle;
import java.util.List;
import com.dotmarketing.util.Config;
import java.util.*;
import com.dotmarketing.cms.factories.*;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.portlets.workflows.struts.*;
import com.dotmarketing.portlets.workflows.model.*;
import javax.portlet.WindowState;
import com.dotmarketing.beans.WebAsset;
import java.util.ArrayList;
import com.dotmarketing.portlets.htmlpages.model.HTMLPage;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.files.model.File;
import com.dotmarketing.portlets.containers.model.Container;
import com.dotmarketing.portlets.links.model.Link;
import com.dotmarketing.portlets.templates.model.Template;
import com.dotmarketing.factories.InodeFactory;
import com.dotmarketing.portlets.structure.model.Structure;
import com.dotmarketing.portlets.structure.model.Field;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import com.dotmarketing.util.Parameter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.liferay.util.cal.CalendarUtil;
import java.util.Locale;
import java.text.SimpleDateFormat;
import com.dotmarketing.beans.WebAsset;
import com.dotmarketing.portlets.htmlpages.model.HTMLPage;
import com.dotmarketing.factories.InodeFactory;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.files.model.File;
import com.dotmarketing.portlets.templates.model.Template;
import com.dotmarketing.portlets.links.model.Link;
import com.dotmarketing.portlets.containers.model.Container;
import com.dotmarketing.portlets.structure.factories.StructureFactory;
import java.util.ArrayList;
import org.apache.commons.beanutils.PropertyUtils;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.PermissionAPI;
import com.liferay.portal.NoSuchRoleException;
import com.dotmarketing.business.Role;
import com.dotmarketing.portlets.workflows.business.WorkflowAPI;
import com.dotmarketing.util.WebKeys.WorkflowStatuses;
import com.dotmarketing.business.RoleAPI;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.language.LanguageUtil;

public final class view_005fworkflow_005ftasks_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(9);
    _jspx_dependants.add("/html/portlet/ext/workflows/init.jsp");
    _jspx_dependants.add("/html/common/init.jsp");
    _jspx_dependants.add("/html/portlet/ext/workflows/workflows_js_inc.jsp");
    _jspx_dependants.add("/WEB-INF/tld/c-rt.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-portlet.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-tiles.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody.release();
    _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.release();
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom.release();
    _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/html/common/auth_backend_check.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	String CTX_PATH = (String) application
			.getAttribute(WebKeys.CTX_PATH);
	String CAPTCHA_PATH = (String) application
			.getAttribute(WebKeys.CAPTCHA_PATH);
	String IMAGE_PATH = (String) application
			.getAttribute(WebKeys.IMAGE_PATH);

	String contextPath = PropsUtil.get(PropsUtil.PORTAL_CTX);
	if (contextPath.equals("/")) {
		contextPath = "";
	}

	String COMMON_IMG = null;

	Company company = PortalUtil.getCompany(request);

	User user = null;
	try {
		user = PortalUtil.getUser(request);
	} catch (NoSuchUserException nsue) {
	}

	boolean signedIn = false;

	if (user == null) {
		user = company.getDefaultUser();
	} else {
		signedIn = true;
	}

	Locale locale = (Locale) session
			.getAttribute(org.apache.struts.Globals.LOCALE_KEY);
	if (locale == null) {

		// Locale should never be null except when the TCK tests invalidate the session

		locale = user.getLocale();
	}
	if(UtilMethods.isSet(request.getParameter("switchLocale"))){
		Locale[] locales = LanguageUtil.getAvailableLocales();
		for (int i = 0; i < locales.length; i++) { 
			String test = locales[i].getLanguage() + "_" + locales[i].getCountry();
			if(test.equals(request.getParameter("switchLocale"))){
				locale = locales[i];
				session.setAttribute(org.apache.struts.Globals.LOCALE_KEY, locale);
				break;
			}
		}
		
	}else{//DOTCMS-5013
		session.setAttribute(org.apache.struts.Globals.LOCALE_KEY, locale);
	}

	TimeZone timeZone = user.getTimeZone();
	if (timeZone == null) {
		timeZone = company.getTimeZone();
	}

	Layout layout = (Layout) request.getAttribute(WebKeys.LAYOUT);
	Layout[] layouts = (Layout[]) request.getAttribute(WebKeys.LAYOUTS);

	String layoutId = null;
	if (layout != null) {
		layoutId = layout.getId();
	}

	//String portletGroupId = PortalUtil.getPortletGroupId(layoutId);

	int RES_NARROW = 0;
	int RES_TOTAL = 0;
	int RES_WIDE = 0;


//	Skin skin = user.getSkin();

	String SKIN_CSS_IMG = null;


	String SKIN_COMMON_IMG =null;
	String SKIN_IMG =null;

      out.write('\n');
      out.write('\n');

	try {
		String hostId = (String) session.getAttribute(com.dotmarketing.util.WebKeys.CMS_SELECTED_HOST_ID);

		com.dotmarketing.business.web.HostWebAPI hostApi = com.dotmarketing.business.web.WebAPILocator.getHostWebAPI();
		com.dotmarketing.beans.Host currentHost = hostApi.find(hostId, user, false);
		if (currentHost.isArchived()) {
			List<com.dotmarketing.beans.Host> hosts = hostApi.findAll(user, false);
			for (com.dotmarketing.beans.Host host : hosts) {
				if (!host.isSystemHost() && !host.isArchived()) {
					session.setAttribute(com.dotmarketing.util.WebKeys.CMS_SELECTED_HOST_ID, host
							.getIdentifier());
					break;
				}
			}
		}
	} catch (Exception e) {

	}
	String DOTCMS_PORTAL_PAGE_TITLE="dotCMS : " + LanguageUtil.get(pageContext, "Enterprise-Web-Content-Management");

	try{
		if(request.getParameter("p_p_id") != null 
				&& ! LanguageUtil.get(pageContext, "javax.portlet.title." + request.getParameter("p_p_id")).equals("javax.portlet.title." + request.getParameter("p_p_id")) ){
			DOTCMS_PORTAL_PAGE_TITLE = LanguageUtil.get(pageContext, "javax.portlet.title." + request.getParameter("p_p_id") ) + " : dotCMS";
			
		}
		
	}
	catch(Exception e){
		
	}





      out.write('\n');
      //  portlet:defineObjects
      com.liferay.portlet.taglib.DefineObjectsTag _jspx_th_portlet_005fdefineObjects_005f0 = (com.liferay.portlet.taglib.DefineObjectsTag) _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody.get(com.liferay.portlet.taglib.DefineObjectsTag.class);
      _jspx_th_portlet_005fdefineObjects_005f0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005fdefineObjects_005f0.setParent(null);
      int _jspx_eval_portlet_005fdefineObjects_005f0 = _jspx_th_portlet_005fdefineObjects_005f0.doStartTag();
      if (_jspx_th_portlet_005fdefineObjects_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody.reuse(_jspx_th_portlet_005fdefineObjects_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody.reuse(_jspx_th_portlet_005fdefineObjects_005f0);
      javax.portlet.PortletConfig portletConfig = null;
      javax.portlet.RenderRequest renderRequest = null;
      javax.portlet.RenderResponse renderResponse = null;
      portletConfig = (javax.portlet.PortletConfig) _jspx_page_context.findAttribute("portletConfig");
      renderRequest = (javax.portlet.RenderRequest) _jspx_page_context.findAttribute("renderRequest");
      renderResponse = (javax.portlet.RenderResponse) _jspx_page_context.findAttribute("renderResponse");
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<style type=\"text/css\">\n");
      out.write("\t@import \"/html/portlet/ext/workflows/schemes/workflow.css\";\n");
      out.write("\tdt{height:30px;}\n");
      out.write("\tdd{height:30px;}\n");
      out.write("</style>\n");
      out.write("\n");


	WorkflowSearcher searcher = (WorkflowSearcher) session.getAttribute(com.dotmarketing.util.WebKeys.WORKFLOW_SEARCHER);
	if(searcher ==null){

		Map<String, Object>  newMap = new HashMap<String, Object>();


		newMap.putAll(request.getParameterMap());

		searcher = new WorkflowSearcher(newMap, user);

	}
	if(!searcher.isOpen() && ! searcher.isClosed()){
		searcher.setOpen(true);
	}

	Structure structure = null;


    boolean isAdministrator = APILocator.getRoleAPI().doesUserHaveRole(user, APILocator.getRoleAPI().loadCMSAdminRole())
                               || APILocator.getRoleAPI().doesUserHaveRole(user,RoleAPI.WORKFLOW_ADMIN_ROLE_KEY);
	List<Role> roles = APILocator.getRoleAPI().loadRolesForUser(user.getUserId());

    Role assignedTo  = APILocator.getRoleAPI().loadRoleById(searcher.getAssignedTo());
    Role myRole  = APILocator.getRoleAPI().getUserRole(user);

    if(assignedTo ==null){
    	assignedTo = myRole;
    }

    List<WorkflowScheme> schemes = APILocator.getWorkflowAPI().findSchemes(false);


	Map myMap = new HashMap();

	myMap.put("struts_action", new String[] { "/ext/workflows/view_workflow_tasks" });

	String referer = com.dotmarketing.util.PortletURLUtil.getActionURL(request, WindowState.MAXIMIZED
		.toString(), myMap);



      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script language=\"Javascript\">\r\n");
      out.write("\tfunction publish (objId,assetId) {\r\n");
      out.write("\t\tif(confirm('");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Are-you-sure-you-want-to-publish-this-Associated-Type")) );
      out.write("')){\t\r\n");
      out.write("\t\t\tvar href = '");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f0 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f0.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(7,15) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f0.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f0 = _jspx_th_portlet_005factionURL_005f0.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f0.doInitBody();
        }
        do {
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f0(_jspx_th_portlet_005factionURL_005f0, _jspx_page_context))
            return;
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f1(_jspx_th_portlet_005factionURL_005f0, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f2 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f2.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f0);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(10,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f2.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(10,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f2.setValue( referer );
          int _jspx_eval_portlet_005fparam_005f2 = _jspx_th_portlet_005fparam_005f2.doStartTag();
          if (_jspx_th_portlet_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f2);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f2);
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f0);
      out.write("&inode='+objId+'&asset_inode='+assetId;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\tdocument.location.href = href;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\tfunction unpublish(objId,assetId) {\r\n");
      out.write("\t\tif(confirm('");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Are-you-sure-you-want-to-un-publish-this-Associated-Type")) );
      out.write("')){\r\n");
      out.write("\t\t\tvar href = '");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f1 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f1.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f1.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(19,15) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f1.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f1 = _jspx_th_portlet_005factionURL_005f1.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f1.doInitBody();
        }
        do {
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f3(_jspx_th_portlet_005factionURL_005f1, _jspx_page_context))
            return;
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f4(_jspx_th_portlet_005factionURL_005f1, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f5 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f5.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f1);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(22,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f5.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(22,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f5.setValue( referer );
          int _jspx_eval_portlet_005fparam_005f5 = _jspx_th_portlet_005fparam_005f5.doStartTag();
          if (_jspx_th_portlet_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f5);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f5);
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f1);
      out.write("&inode='+objId+'&asset_inode='+assetId;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\tdocument.location.href = href;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\tfunction archive (objId, assetId) {\r\n");
      out.write("\t\tif(confirm('");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Are-you-sure-you-want-to-archive-this-Associated-Type")) );
      out.write("')){\r\n");
      out.write("\t   \t\tvar href = '");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f2 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f2.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f2.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(32,18) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f2.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f2 = _jspx_th_portlet_005factionURL_005f2.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f2.doInitBody();
        }
        do {
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f6(_jspx_th_portlet_005factionURL_005f2, _jspx_page_context))
            return;
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f7(_jspx_th_portlet_005factionURL_005f2, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f8 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f8.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f2);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(35,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f8.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(35,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f8.setValue( referer );
          int _jspx_eval_portlet_005fparam_005f8 = _jspx_th_portlet_005fparam_005f8.doStartTag();
          if (_jspx_th_portlet_005fparam_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f8);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f8);
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f2);
      out.write("&inode='+objId+'&asset_inode='+assetId;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\tdocument.location.href = href;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tfunction unarchive (objId, assetId) {\r\n");
      out.write("\t\tif(confirm('");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Are-you-sure-you-want-to-un-archive-this-Associated-Type")) );
      out.write("')){\r\n");
      out.write("\t\t\tvar href = '");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f3 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f3.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f3.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(45,15) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f3.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f3 = _jspx_th_portlet_005factionURL_005f3.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f3.doInitBody();
        }
        do {
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f9(_jspx_th_portlet_005factionURL_005f3, _jspx_page_context))
            return;
          out.write("'\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f10(_jspx_th_portlet_005factionURL_005f3, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f11 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f11.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f3);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(48,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f11.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(48,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f11.setValue( referer );
          int _jspx_eval_portlet_005fparam_005f11 = _jspx_th_portlet_005fparam_005f11.doStartTag();
          if (_jspx_th_portlet_005fparam_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f11);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f11);
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f3.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f3);
      out.write("&inode='+objId+'&asset_inode='+assetId;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\tdocument.location.href = href;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction previewHTMLPage (objId, referer) {\r\n");
      out.write("\t\ttop.location='");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f4 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f4.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f4.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(56,16) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f4.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f4 = _jspx_th_portlet_005factionURL_005f4.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f4.doInitBody();
        }
        do {
          if (_jspx_meth_portlet_005fparam_005f12(_jspx_th_portlet_005factionURL_005f4, _jspx_page_context))
            return;
          if (_jspx_meth_portlet_005fparam_005f13(_jspx_th_portlet_005factionURL_005f4, _jspx_page_context))
            return;
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f4.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f4);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f4);
      out.write("&inode=' + objId + '&referer=' + referer;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction deleteWorkFlowTask(inode) {\r\n");
      out.write("\t");

		java.util.Map viewParams = new java.util.HashMap();
		viewParams.put("struts_action",new String[] {"/ext/workflows/view_workflow_tasks"});
		String viewReferer = com.dotmarketing.util.PortletURLUtil.getRenderURL(request,WindowState.MAXIMIZED.toString(),viewParams);
	
      out.write("\r\n");
      out.write("\t  if(confirm('");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Are-you-sure-you-want-to-delete-this-workflow-task")) );
      out.write("')){\r\n");
      out.write("\t\t\tvar href= '");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f5 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f5.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f5.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(66,14) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f5.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f5 = _jspx_th_portlet_005factionURL_005f5.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f5.doInitBody();
        }
        do {
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f14(_jspx_th_portlet_005factionURL_005f5, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          if (_jspx_meth_portlet_005fparam_005f15(_jspx_th_portlet_005factionURL_005f5, _jspx_page_context))
            return;
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f16 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f16.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f5);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(69,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f16.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(69,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f16.setValue( viewReferer );
          int _jspx_eval_portlet_005fparam_005f16 = _jspx_th_portlet_005fparam_005f16.doStartTag();
          if (_jspx_th_portlet_005fparam_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f16);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f16);
          out.write("';\r\n");
          out.write("\t\t\thref = href + '");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f5.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f5);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f5);
      out.write("&inode='+inode;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tdocument.location.href = href;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t//Layout Initialization\r\n");
      out.write("\tvar browserLoaded = false;\r\n");
      out.write("\t\r\n");
      out.write("\tfunction  resizeBrowser(){\r\n");
      out.write("\t\tif(browserLoaded) return;\r\n");
      out.write("\t\tbrowserLoaded=true;\r\n");
      out.write("\t    var viewport = dijit.getViewport();\r\n");
      out.write("\t    var viewport_height = viewport.h;\r\n");
      out.write("\t    var e =  dojo.byId(\"borderContainer\");\r\n");
      out.write("\t    dojo.style(e, \"height\", viewport_height -150 + \"px\");\r\n");
      out.write("\t    var bc = dijit.byId('borderContainer');\r\n");
      out.write("\t    if(bc != undefined){\r\n");
      out.write("\t    \tbc.resize();\r\n");
      out.write("\t    }\r\n");
      out.write("\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdojo.connect(window, \"onresize\", this, \"resizeBrowser\");\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t    dojo.declare(\"dotcms.dijit.contentlet.ContentAdmin\", null, {\r\n");
      out.write("\t    \tcontentletIdentifier : \"\",\r\n");
      out.write("\t    \tcontentletInode : \"\",\r\n");
      out.write("\t    \tlanguageID : \"\",\r\n");
      out.write("\t    \twfActionId:\"\",\r\n");
      out.write("\t    \tconstructor : function(contentletIdentifier, contentletInode,languageId ) {\r\n");
      out.write("\t    \t\tthis.contentletIdentifier = contentletIdentifier;\r\n");
      out.write("\t    \t\tthis.contentletInode =contentletInode;\r\n");
      out.write("\t    \t\tthis.languageId=languageId;\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t    \t},\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    \texecuteWfAction: function(wfId, assignable, commentable, inode, showpush){\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t    \t\tthis.wfActionId=wfId;\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t    \t\tif(assignable || commentable || showpush){\r\n");
      out.write("\t    \t\t\t\r\n");
      out.write("\t    \t\t\tvar myCp = dijit.byId(\"contentletWfCP\");\r\n");
      out.write("\t    \t\t\tif (myCp) {\r\n");
      out.write("\t    \t\t\t\tmyCp.destroyRecursive(true);\r\n");
      out.write("\t    \t\t\t}\r\n");
      out.write("\t    \t\t\t\r\n");
      out.write("\t    \t\t\tvar dia = dijit.byId(\"contentletWfDialog\");\r\n");
      out.write("\t    \t\t\tif(dia){\r\n");
      out.write("\t    \t\t\t\tdia.destroyRecursive();\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\t}\r\n");
      out.write("\t    \t\t\tdia = new dijit.Dialog({\r\n");
      out.write("\t    \t\t\t\tid\t\t\t:\t\"contentletWfDialog\",\r\n");
      out.write("\t    \t\t\t\ttitle\t\t: \t\"");
      out.print(LanguageUtil.get(pageContext, "Workflow-Actions"));
      out.write("\",\r\n");
      out.write("\t\t\t\t\t\tstyle\t\t:\t\"min-width:500px;min-height:250px;\"\r\n");
      out.write("\t    \t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t  \t\t\t\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\tmyCp = new dojox.layout.ContentPane({\r\n");
      out.write("\t    \t\t\t\tid \t\t\t: \"contentletWfCP\",\r\n");
      out.write("\t    \t\t\t\tstyle\t\t:\t\"minwidth:500px;min-height:250px;margin:auto;\"\r\n");
      out.write("\t    \t\t\t}).placeAt(\"contentletWfDialog\");\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\tdia.show();\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\tvar r = Math.floor(Math.random() * 1000000000);\r\n");
      out.write("\t\t\t\t\tvar url = \"/DotAjaxDirector/com.dotmarketing.portlets.workflows.ajax.WfTaskAjax?cmd=renderAction&actionId=\" + wfId \r\n");
      out.write("\t\t\t\t\t\t\t+ \"&inode=\" + inode \r\n");
      out.write("\t\t\t\t\t\t\t+ \"&showpush=\" + showpush \r\n");
      out.write("\t\t\t\t\t\t\t+ \"&publishDate=");
      out.print(structure!=null?structure.getPublishDateVar():"");
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t+ \"&expireDate=");
      out.print(structure!=null?structure.getExpireDateVar():"");
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t+ \"&structureInode=");
      out.print(structure!=null?structure.getInode():"");
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t+ \"&r=\" + r;\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t    \t\t\tmyCp.attr(\"href\", url);\r\n");
      out.write("\t    \t\t\treturn;\r\n");
      out.write("\t    \t\t}\r\n");
      out.write("\t    \t\telse{\r\n");
      out.write("\t    \t\t\tdojo.byId(\"wfActionId\").value=this.wfActionId;\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\tthis.executeWorkflow();\r\n");
      out.write("\t    \t\t}\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t    \t},\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    \tsaveAssign : function(){  \t\t\r\n");
      out.write("\t    \t\tvar assignRole = (dijit.byId(\"taskAssignmentAux\"))\r\n");
      out.write("\t\t\t\t? dijit.byId(\"taskAssignmentAux\").getValue()\r\n");
      out.write("\t\t\t\t\t: (dojo.byId(\"taskAssignmentAux\"))\r\n");
      out.write("\t\t\t\t\t\t? dojo.byId(\"taskAssignmentAux\").value\r\n");
      out.write("\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tif(!assignRole || assignRole.length ==0 ){\r\n");
      out.write("\t\t\t\t\tshowDotCMSSystemMessage(\"");
      out.print(LanguageUtil.get(pageContext, "Assign-To-Required"));
      out.write("\");\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar comments = (dijit.byId(\"taskCommentsAux\"))\r\n");
      out.write("\t\t\t\t\t? dijit.byId(\"taskCommentsAux\").getValue()\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"taskCommentsAux\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.byId(\"taskCommentsAux\").value\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\t    \t\tvar wfActionAssign \t\t= assignRole;\r\n");
      out.write("\t    \t\tvar selectedItem \t\t= \"\";\r\n");
      out.write("\t    \t\tvar wfConId \t\t\t= dojo.byId(\"wfConId\").value;\r\n");
      out.write("\t    \t\tvar wfActionId \t\t\t= this.wfActionId;\r\n");
      out.write("\t    \t\tvar wfActionComments \t= comments;\r\n");
      out.write("\r\n");
      out.write("\t    \t\tvar dia = dijit.byId(\"contentletWfDialog\").hide();\r\n");
      out.write("\r\n");
      out.write("\t    \t\t// BEGIN: PUSH PUBLISHING ACTIONLET\r\n");
      out.write("\t\t\t\tvar publishDate = (dijit.byId(\"wfPublishDateAux\"))\r\n");
      out.write("\t\t\t\t\t? dojo.date.locale.format(dijit.byId(\"wfPublishDateAux\").getValue(),{datePattern: \"yyyy-MM-dd\", selector: \"date\"})\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"wfPublishDateAux\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.date.locale.format(dojo.byId(\"wfPublishDateAux\").value,{datePattern: \"yyyy-MM-dd\", selector: \"date\"})\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar publishTime = (dijit.byId(\"wfPublishTimeAux\"))\r\n");
      out.write("\t\t\t\t\t? dojo.date.locale.format(dijit.byId(\"wfPublishTimeAux\").getValue(),{timePattern: \"H-m\", selector: \"time\"})\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"wfPublishTimeAux\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.date.locale.format(dojo.byId(\"wfPublishTimeAux\").value,{timePattern: \"H-m\", selector: \"time\"})\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar expireDate = (dijit.byId(\"wfExpireDateAux\"))\r\n");
      out.write("\t\t\t\t\t? dijit.byId(\"wfExpireDateAux\").getValue()!=null ? dojo.date.locale.format(dijit.byId(\"wfExpireDateAux\").getValue(),{datePattern: \"yyyy-MM-dd\", selector: \"date\"}) : \"\"\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"wfExpireDateAux\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.byId(\"wfExpireDateAux\").value!=null ? dojo.date.locale.format(dojo.byId(\"wfExpireDateAux\").value,{datePattern: \"yyyy-MM-dd\", selector: \"date\"}) : \"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar expireTime = (dijit.byId(\"wfExpireTimeAux\"))\r\n");
      out.write("\t\t\t\t\t? dijit.byId(\"wfExpireTimeAux\").getValue()!=null ? dojo.date.locale.format(dijit.byId(\"wfExpireTimeAux\").getValue(),{timePattern: \"H-m\", selector: \"time\"}) : \"\"\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"wfExpireTimeAux\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.byId(\"wfExpireTimeAux\").value!=null ? dojo.date.locale.format(dojo.byId(\"wfExpireTimeAux\").value,{timePattern: \"H-m\", selector: \"time\"}) : \"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\t\t\t\tvar neverExpire = (dijit.byId(\"wfNeverExpire\"))\r\n");
      out.write("\t\t\t\t\t? dijit.byId(\"wfNeverExpire\").getValue()\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"wfNeverExpire\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.byId(\"wfNeverExpire\").value\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar whereToSend = (dijit.byId(\"whereToSend\"))\r\n");
      out.write("\t\t\t\t\t? dijit.byId(\"whereToSend\").getValue()\r\n");
      out.write("\t\t\t\t\t\t: (dojo.byId(\"whereToSend\"))\r\n");
      out.write("\t\t\t\t\t\t\t? dojo.byId(\"whereToSend\").value\r\n");
      out.write("\t\t\t\t\t\t\t\t\t: \"\";\r\n");
      out.write("\t\t\t\t// END: PUSH PUBLISHING ACTIONLET\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfActionAssign\").value=assignRole;\r\n");
      out.write("     \t\t\t\tdojo.byId(\"wfActionComments\").value=comments;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfActionId\").value=this.wfActionId;\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t// BEGIN: PUSH PUBLISHING ACTIONLET\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfPublishDate\").value=publishDate;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfPublishTime\").value=publishTime;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfExpireDate\").value=expireDate;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfExpireTime\").value=expireTime;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"wfNeverExpire\").value=neverExpire;\r\n");
      out.write("\t\t\t\t\tdojo.byId(\"whereToSend\").value=whereToSend;\r\n");
      out.write("\t\t\t\t\t// END: PUSH PUBLISHING ACTIONLET\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\tthis.executeWorkflow();\r\n");
      out.write("\t    \t},\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    \texecuteWorkflow : function (){\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t    \t\tdijit.byId('savingContentDialog').show();\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar xhrArgs = {\r\n");
      out.write("\t\t\t\t\tform: dojo.byId(\"submitWorkflowTaskFrm\"),\r\n");
      out.write("\t\t\t\t\thandleAs: \"text\",\r\n");
      out.write("\t\t\t\t\tload: function(data) {\r\n");
      out.write("\t\t\t\t\t\tshowDotCMSSystemMessage(\"");
      out.print(LanguageUtil.get(pageContext, "Workflow-executed"));
      out.write("\");\r\n");
      out.write("\t\t\t\t\t\twindow.location='");
      //  portlet:actionURL
      com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f6 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
      _jspx_th_portlet_005factionURL_005f6.setPageContext(_jspx_page_context);
      _jspx_th_portlet_005factionURL_005f6.setParent(null);
      // /html/portlet/ext/workflows/workflows_js_inc.jsp(257,23) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_portlet_005factionURL_005f6.setWindowState( WindowState.MAXIMIZED.toString() );
      int _jspx_eval_portlet_005factionURL_005f6 = _jspx_th_portlet_005factionURL_005f6.doStartTag();
      if (_jspx_eval_portlet_005factionURL_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_portlet_005factionURL_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_portlet_005factionURL_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_portlet_005factionURL_005f6.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          if (_jspx_meth_portlet_005fparam_005f17(_jspx_th_portlet_005factionURL_005f6, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  portlet:param
          com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f18 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_portlet_005fparam_005f18.setPageContext(_jspx_page_context);
          _jspx_th_portlet_005fparam_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f6);
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(259,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f18.setName("referer");
          // /html/portlet/ext/workflows/workflows_js_inc.jsp(259,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_portlet_005fparam_005f18.setValue( referer );
          int _jspx_eval_portlet_005fparam_005f18 = _jspx_th_portlet_005fparam_005f18.doStartTag();
          if (_jspx_th_portlet_005fparam_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f18);
            return;
          }
          _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f18);
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f6.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_portlet_005factionURL_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_portlet_005factionURL_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f6);
        return;
      }
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f6);
      out.write("';\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror: function(error) {\r\n");
      out.write("\t\t\t\t\t\tshowDotCMSSystemMessage(error);\r\n");
      out.write("\t\t\t\t\t\tdijit.byId('savingContentDialog').hide();\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tdojo.xhrPost(xhrArgs);\r\n");
      out.write("\r\n");
      out.write("\t    \t\t\r\n");
      out.write("\t    \t}\r\n");
      out.write("\r\n");
      out.write("\t    });\r\n");
      out.write("\t    var contentAdmin = new dotcms.dijit.contentlet.ContentAdmin();\r\n");
      out.write("</script>");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\tdojo.require(\"dijit.form.FilteringSelect\");\n");
      out.write("\tdojo.require(\"dotcms.dojo.data.RoleReadStore\");\n");
      out.write("\tdojo.require(\"dotcms.dojo.data.RoleReadStore\");\n");
      out.write("\tdojo.require(\"dojox.layout.ContentPane\");\n");
      out.write("\n");
      out.write("\tfunction doFilter () {\n");
      out.write("\n");
      out.write("\t\tvar url=\"\";\n");
      out.write("\n");
      out.write("\t\tif(!dijit.byId(\"showOpen\").checked && !dijit.byId(\"showClosed\").checked){\n");
      out.write("\t\t\tdijit.byId(\"showOpen\").setValue(true) ;\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tvar container = dojo.byId(\"filterTasksFrm\");\n");
      out.write("\t\tvar widgets = dojo.query(\"[widgetId]\", container).map(dijit.byNode);\n");
      out.write("\t\tdojo.forEach(widgets, function(inputElem){\n");
      out.write("\t\t\tif(\"checkbox\" == inputElem.type){\n");
      out.write("\t\t\t\turl = url + \"&\" + inputElem.name +\"=\" +inputElem.checked ;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\telse{\n");
      out.write("\t\t\t\turl = url + \"&\" + inputElem.name +\"=\" +inputElem.value ;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t");
if(isAdministrator) {
      out.write("\n");
      out.write("\t\t     if(show4All) {\n");
      out.write("\t\t    \t url = url + \"&show4all=true\";\n");
      out.write("\t\t     }\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("\n");
      out.write("\t\trefreshTaskList(url);\n");
      out.write("\n");
      out.write("\n");
      out.write("\t}\n");
      out.write("\tvar lastUrlParams ;\n");
      out.write("\n");
      out.write("\tfunction refreshTaskList(urlParams){\n");
      out.write("\t\tlastUrlParams = urlParams;\n");
      out.write("\t\tvar r = Math.floor(Math.random() * 1000000000);\n");
      out.write("\t\tvar url = \"/html/portlet/ext/workflows/view_tasks_list.jsp?r=\" + r + urlParams;\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tvar myCp = dijit.byId(\"workflowTaskListCp\");\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tif (myCp) {\n");
      out.write("\t\t\tdojo.attr(myCp,\"content\",\"\");\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\tmyCp = new dojox.layout.ContentPane({\n");
      out.write("\t\t\t\tid : \"workflowTaskListCp\"\n");
      out.write("\t\t\t}).placeAt(\"hangTaskListHere\");\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tmyCp.attr(\"href\", url);\n");
      out.write("\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction doOrderBy (newOrder) {\n");
      out.write("\n");
      out.write("\t\tdojo.byId(\"orderBy\").value= newOrder;\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tvar newURL = \"\";\n");
      out.write("\t\tvar x  =lastUrlParams.split(\"&\");\n");
      out.write("\n");
      out.write("        for (i = 0; i < x.length; i++) {\n");
      out.write("            if (x[i].indexOf(\"orderBy\") < 0) {\n");
      out.write("\n");
      out.write("                if (x[i].length > 0) {\n");
      out.write("                    if (x[i].indexOf(\"&\") == 0) {\n");
      out.write("                        newURL += x[i];\n");
      out.write("                    } else {\n");
      out.write("                        newURL += \"&\" + x[i];\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("\t\tnewURL+=\"&orderBy=\" +newOrder;\n");
      out.write("\n");
      out.write("\t\trefreshTaskList(newURL);\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("var stepStore = new dojo.data.ItemFileReadStore({url:\"/DotAjaxDirector/com.dotmarketing.portlets.workflows.ajax.WfStepAjax?cmd=listByScheme\"});\n");
      out.write("var emptyData = { \"identifier\" : \"id\", \"label\" : \"name\", \"items\": [{ name: '',id: '' }] };\n");
      out.write("var emptyStore = new dojo.data.ItemFileReadStore({data:emptyData});\n");
      out.write("var daysData= { \"identifier\" : \"d\", \"label\" : \"days\", \"items\":\n");
      out.write("\t[{d:1},{d:2},{d:5},{d:10},{d:15},{d:20},{d:30},{d:40},{d:50},{d:60}]};\n");
      out.write("var daysOldStore = new dojo.data.ItemFileReadStore({data:daysData});\n");
      out.write("\n");
      out.write("var myRoleReadStore = new dotcms.dojo.data.RoleReadStore({nodeId: \"assignedTo\", includeFake:false});\n");
      out.write("\n");
      out.write("dojo.ready(function(){\n");
      out.write("\n");
      out.write("\tif(dojo.isIE){\n");
      out.write("    \tsetTimeout(function(){\n");
      out.write("        \tvar randomParam = Math.floor((Math.random()*10000)+1);\n");
      out.write("            var myRoleReadStoreURL = myRoleReadStore.url;\n");
      out.write("            var dummyVar = new Array();\n");
      out.write("            myRoleReadStore.url = myRoleReadStoreURL+\"?randomParam=\"+randomParam;\n");
      out.write("            myRoleReadStore.fetch({onComplete: dummyVar});\n");
      out.write("        },100);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
if(isAdministrator){
      out.write("\n");
      out.write("    var assignedTo = new dijit.form.FilteringSelect({\n");
      out.write("        id: \"assignedTo\",\n");
      out.write("        name: \"assignedTo\",\n");
      out.write("        store: myRoleReadStore,\n");
      out.write("        searchDelay: 300,\n");
      out.write("        pageSize: 30,\n");
      out.write("        required: false,\n");
      out.write("        value: \"");
      out.print(assignedTo.getId());
      out.write("\",\n");
      out.write("        onClick:function(){\n");
      out.write("        \tdijit.byId(\"assignedTo\").set(\"displayedValue\",\"\");\n");
      out.write("        \tdijit.byId(\"assignedTo\").loadDropDown();\n");
      out.write("        },\n");
      out.write("        onChange:function(){\n");
      out.write("        \tdoFilter();\n");
      out.write("        }\n");
      out.write("    },\n");
      out.write("    \"assignedTo\");\n");
      out.write("    doFilter();\n");
}
      out.write("\n");
      out.write("\n");
      out.write("\tvar stepId = new dijit.form.FilteringSelect({\n");
      out.write("\t    id: \"stepId\",\n");
      out.write("\t    name: \"stepId\",\n");
      out.write("\t    store: emptyStore,\n");
      out.write("\t    searchDelay:300,\n");
      out.write("\t    pageSize:20,\n");
      out.write("\t    required:false,\n");
      out.write("\t    onChange:function(){\n");
      out.write("\t    \tdoFilter();\n");
      out.write("\t    }\n");
      out.write("\n");
      out.write("\t},\"stepId\");\n");
      out.write("\n");
      out.write("\tvar olderThanCombo = new dijit.form.ComboBox({\n");
      out.write("        id:\"daysold\",\n");
      out.write("        name:\"daysold\",\n");
      out.write("        store:daysOldStore,\n");
      out.write("        required:false,\n");
      out.write("        value:\"\",\n");
      out.write("        searchAttr:\"d\"\n");
      out.write("    },\"daysold\");\n");
      out.write("\n");
      out.write("\n");
      out.write("\tdoFilter();\n");
      out.write("\n");
      out.write("});\n");
      out.write("\n");
      out.write("\tvar emptyData = {\"identifier\" : \"id\",\"label\" : \"name\",\"items\": [{ name: '', id: ''}]};\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction updateSteps(){\n");
      out.write("\t\tvar schemeId = dijit.byId(\"schemeId\").value;\n");
      out.write("\t\tvar stepId = dijit.byId(\"stepId\");\n");
      out.write("\t\tstepId.store= emptyStore;\n");
      out.write("\t\tdojo.byId(\"stepId\").value =\"\";\n");
      out.write("\t\tif(schemeId){\n");
      out.write("\t\t\tvar myUrl = \"/DotAjaxDirector/com.dotmarketing.portlets.workflows.ajax.WfStepAjax?cmd=listByScheme&schemeId=\" + schemeId;\n");
      out.write("\t\t\tdijit.byId(\"stepId\").set('store',new dojo.data.ItemFileReadStore({url:myUrl}));\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tfunction assignedToMe(){\n");
      out.write("\t\t");
if(isAdministrator){
      out.write("\n");
      out.write("             disable4AllUsers();\n");
      out.write("        ");
}
      out.write("\n");
      out.write("\n");
      out.write("\t\tvar assignedTo = dijit.byId(\"assignedTo\");\n");
      out.write("\t\tassignedTo.displayedValue=\"\";\n");
      out.write("\t\tassignedTo.setValue(\"");
      out.print(myRole.getId());
      out.write("\");\n");
      out.write("\t\tdoFilter();\n");
      out.write("\t}\n");
      out.write("\t");
if(isAdministrator){
      out.write("\n");
      out.write("\t    var show4All=false;\n");
      out.write("\t\tfunction showTasks4AllUsers() {\n");
      out.write("\t\t\tif(show4All) {\n");
      out.write("\t\t\t\tdisable4AllUsers();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\telse {\n");
      out.write("\t\t\t\tvar assignedTo = dijit.byId(\"assignedTo\");\n");
      out.write("\t\t        assignedTo.displayedValue=\"\";\n");
      out.write("\n");
      out.write("\t\t        assignedTo.attr(\"disabled\",\"true\");\n");
      out.write("\t\t        dojo.style(dojo.byId(\"showAllLink\"),\"fontWeight\",\"bold\");\n");
      out.write("\t\t        show4All=true;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tdoFilter();\n");
      out.write("\t\t}\n");
      out.write("\t\tfunction disable4AllUsers() {\n");
      out.write("\t\t\tshow4All=false;\n");
      out.write("\t\t\tvar assignedTo = dijit.byId(\"assignedTo\");\n");
      out.write("\t\t\tassignedTo.attr(\"disabled\",false);\n");
      out.write("\t\t\tdojo.style(dojo.byId(\"showAllLink\"),\"fontWeight\",\"normal\");\n");
      out.write("\t\t}\n");
      out.write("\t");
}
      out.write("\n");
      out.write("\tfunction resetFilters(){\n");
      out.write("\n");
      out.write("\t\tdijit.byId(\"showme\").set(\"checked\", \"true\");\n");
      out.write("\n");
      out.write("\t\tdijit.byId(\"daysold\").setValue(\"\");\n");
      out.write("\n");
      out.write("\t\tvar stepId = dijit.byId(\"stepId\");\n");
      out.write("\t\tstepId.setValue(\"\");\n");
      out.write("\n");
      out.write("\t\t");
if(isAdministrator){
      out.write("\n");
      out.write("\t        disable4AllUsers();\n");
      out.write("\t   ");
}
      out.write("\n");
      out.write("\n");
      out.write("\t\tvar assignedTo = dijit.byId(\"assignedTo\");\n");
      out.write("\t\tassignedTo.displayedValue=\"\";\n");
      out.write("\t\tassignedTo.setValue(\"");
      out.print(myRole.getId());
      out.write("\");\n");
      out.write("\n");
      out.write("\t\tdijit.byId(\"keywords\").setValue(\"\");\n");
      out.write("\n");
      out.write("\t\tdijit.byId(\"showOpen\").setValue(true);\n");
      out.write("\t\tdijit.byId(\"showClosed\").setValue(false);\n");
      out.write("\n");
      out.write("\t\tvar schemeId = dijit.byId(\"schemeId\");\n");
      out.write("\t\tschemeId.setValue(\"\");\n");
      out.write("\n");
      out.write("\t\tdoFilter();\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tfunction editTask(id,langId){\n");
      out.write("\t\tvar url = \"");
      if (_jspx_meth_portlet_005factionURL_005f7(_jspx_page_context))
        return;
      out.write("\";\n");
      out.write("\t\turl = url.replace(\"REPLACEME\", id);\n");
      out.write("\t\turl = url.replace(\"LANGUAGE\", langId);\n");
      out.write("\t\twindow.location=url;\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("/*\tdojo.ready(function(){\n");
      out.write("\n");
      out.write("\tupdateSteps();\n");
      out.write("\t})\n");
      out.write("*/\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction checkAll(){\n");
      out.write("\t\tvar x = dijit.byId(\"checkAllCkBx\").checked;\n");
      out.write("\n");
      out.write("\t\tdojo.query(\".taskCheckBox\").forEach(function(node){\n");
      out.write("\t\t\tdijit.byNode(node).setValue(x);\n");
      out.write("\t\t})\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction excuteWorkflowAction(){\n");
      out.write("\t\tvar actionId = dijit.byId(\"performAction\").getValue();\n");
      out.write("\n");
      out.write("\t\tif(! actionId || actionId.length <1){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(\"");
      out.print(LanguageUtil.get(pageContext, "Please-select-an-action"));
      out.write("\", true);\n");
      out.write("\t\t\treturn;\n");
      out.write("\t\t}\n");
      out.write("\t\tdojo.byId(\"wfActionId\").value =actionId;\n");
      out.write("\n");
      out.write("\t\tvar hasChecks = false;\n");
      out.write("\t\tvar cons =\"\";\n");
      out.write("\t\tdojo.query(\".taskCheckBox\").forEach(function(node){\n");
      out.write("\t\t\tvar check = dijit.byNode(node);\n");
      out.write("\t\t\tif(check.getValue()){\n");
      out.write("\t\t\t\tcons = cons +  check.id + \",\";\n");
      out.write("\t\t\t\thasChecks=true;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tif(!hasChecks){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(\"");
      out.print(LanguageUtil.get(pageContext, "Please-select-a-task"));
      out.write("\", true);\n");
      out.write("\t\t\treturn;\n");
      out.write("\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tdojo.byId(\"wfCons\").value=cons;\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tactionStore.fetch({query: {id:actionId}, onComplete:function(item){\n");
      out.write("\t\t\tif(item[0].assignable ==\"true\" || item[0].commentable == \"true\"){\n");
      out.write("\t\t\t\tvar dia = dijit.byId(\"contentletWfDialog\");\n");
      out.write("    \t\t\tif(dia){\n");
      out.write("    \t\t\t\tdia.destroyRecursive();\n");
      out.write("\n");
      out.write("    \t\t\t}\n");
      out.write("    \t\t\tdia = new dijit.Dialog({\n");
      out.write("    \t\t\t\tid\t\t\t:\t\"contentletWfDialog\",\n");
      out.write("    \t\t\t\ttitle\t\t: \t\"");
      out.print(LanguageUtil.get(pageContext, "Workflow-Actions"));
      out.write("\"\n");
      out.write("    \t\t\t\t});\n");
      out.write("\n");
      out.write("\n");
      out.write("  \t\t\t\tvar myCp = dijit.byId(\"contentletWfCP\");\n");
      out.write("    \t\t\tif(myCp){\n");
      out.write("    \t\t\t\tmyCp.destroyRecursive();\n");
      out.write("\n");
      out.write("    \t\t\t}\n");
      out.write("    \t\t\tmyCp = new dojox.layout.ContentPane({\n");
      out.write("    \t\t\t\tid \t\t\t: \"contentletWfCP\"\n");
      out.write("    \t\t\t}).placeAt(\"contentletWfDialog\");\n");
      out.write("\n");
      out.write("    \t\t\tdia.show();\n");
      out.write("    \t\t\tmyCp.attr(\"href\", \"/DotAjaxDirector/com.dotmarketing.portlets.workflows.ajax.WfTaskAjax?cmd=renderAction&actionId=\" + actionId);\n");
      out.write("    \t\t\treturn;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\telse{\n");
      out.write("\t\t\t\tcontentAdmin.saveAssign();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}});\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tvar contentAdmin = {\n");
      out.write("\t\tsaveAssign:function(){\n");
      out.write("\n");
      out.write("\t\t\tif(dojo.byId(\"taskAssignmentAux\")){\n");
      out.write("\t\t\t\tdojo.byId(\"wfActionAssign\").value=dijit.byId('taskAssignmentAux').getValue();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(dojo.byId(\"taskCommentsAux\")){\n");
      out.write("\t\t\t\tdojo.byId(\"wfActionComments\").value=dijit.byId('taskCommentsAux').getValue();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(dojo.byId(\"contentletWfDialog\")){\n");
      out.write("\t\t\t\tdijit.byId(\"contentletWfDialog\").hide();\n");
      out.write("\t\t\t}\n");
      out.write("\n");
      out.write("\t\t\tdojo.xhrPost({\n");
      out.write("\t\t\t\tform : \"executeTasksFrm\",\n");
      out.write("\t\t\t\ttimeout : 30000,\n");
      out.write("\t\t\t\thandle : function(dataOrError, ioArgs) {\n");
      out.write("\t\t\t\t\tif (dojo.isString(dataOrError)) {\n");
      out.write("\t\t\t\t\t\tif (dataOrError.indexOf(\"FAILURE\") == 0) {\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\tshowDotCMSSystemMessage(dataOrError, true);\n");
      out.write("\t\t\t\t\t\t} else {\n");
      out.write("\t\t\t\t\t\t\tshowDotCMSSystemMessage(\"");
      out.print(LanguageUtil.get(pageContext, "Saved"));
      out.write("\");\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t} else {\n");
      out.write("\t\t\t\t\t\tthis.saveError(\"");
      out.print(LanguageUtil.get(pageContext, "Unable-to-excute-workflows"));
      out.write("\");\n");
      out.write("\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t\tdoFilter();\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t}\n");
      out.write("\t};\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("\t #container {\n");
      out.write("\t   display: table;\n");
      out.write("\t   }\n");
      out.write("\t #table-row  {\n");
      out.write("\t   display: table-row;\n");
      out.write("\t   }\n");
      out.write("\t #cell-left, #cell-right{\n");
      out.write("\t   display: table-cell;\n");
      out.write("\t   }\n");
      out.write("</style>\n");
      //  liferay:box
      com.liferay.taglib.BoxTag _jspx_th_liferay_005fbox_005f0 = (com.liferay.taglib.BoxTag) _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom.get(com.liferay.taglib.BoxTag.class);
      _jspx_th_liferay_005fbox_005f0.setPageContext(_jspx_page_context);
      _jspx_th_liferay_005fbox_005f0.setParent(null);
      // /html/portlet/ext/workflows/view_workflow_tasks.jsp(484,0) name = top type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_liferay_005fbox_005f0.setTop("/html/common/box_top.jsp");
      // /html/portlet/ext/workflows/view_workflow_tasks.jsp(484,0) name = bottom type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_liferay_005fbox_005f0.setBottom("/html/common/box_bottom.jsp");
      int _jspx_eval_liferay_005fbox_005f0 = _jspx_th_liferay_005fbox_005f0.doStartTag();
      if (_jspx_eval_liferay_005fbox_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay_005fbox_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay_005fbox_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay_005fbox_005f0.doInitBody();
        }
        do {
          out.write('\n');
          //  liferay:param
          com.liferay.taglib.ParamTag _jspx_th_liferay_005fparam_005f0 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
          _jspx_th_liferay_005fparam_005f0.setPageContext(_jspx_page_context);
          _jspx_th_liferay_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay_005fbox_005f0);
          // /html/portlet/ext/workflows/view_workflow_tasks.jsp(486,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_liferay_005fparam_005f0.setName("box_title");
          // /html/portlet/ext/workflows/view_workflow_tasks.jsp(486,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_liferay_005fparam_005f0.setValue( LanguageUtil.get(pageContext, "Filtered-Tasks") );
          int _jspx_eval_liferay_005fparam_005f0 = _jspx_th_liferay_005fparam_005f0.doStartTag();
          if (_jspx_th_liferay_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_liferay_005fparam_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fliferay_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_liferay_005fparam_005f0);
          out.write("\n");
          out.write("\n");
          out.write("\n");
          out.write("<!-- START Button Row -->\n");
          out.write("\t<div class=\"buttonBoxLeft\"><h3>");
          out.print(LanguageUtil.get(pageContext, "javax.portlet.title.EXT_21"));
          out.write("</h3></div>\n");
          out.write("\n");
          out.write("<!-- END Button Row -->\n");
          out.write("\n");
          out.write("<!-- START Split Box -->\n");
          out.write("<div dojoType=\"dijit.layout.BorderContainer\" design=\"sidebar\" gutters=\"false\" liveSplitters=\"true\" id=\"borderContainer\" class=\"shadowBox headerBox\" style=\"height:100px;\">\n");
          out.write("\n");
          out.write("<!-- START Left Column -->\n");
          out.write("\t<div dojoType=\"dijit.layout.ContentPane\" splitter=\"false\" region=\"leading\" style=\"width: 350px;\" class=\"lineRight\">\n");
          out.write("\t\t<div style=\"margin-top:48px;\">\n");
          out.write("\t\t\t<div  id=\"filterTasksFrm\">\n");
          out.write("\t\t\t\t<input type=\"hidden\" name=\"cmd\" value=\"filterTasks\">\n");
          out.write("\t\t\t\t<input type=\"hidden\" name=\"orderBy\" id=\"orderBy\" value=\"mod_date desc\">\n");
          out.write("\t\t\t\t<dl>\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Keywords"));
          out.write(":</dt>\n");
          out.write("\t\t\t\t\t<dd><input type=\"text\" dojoType=\"dijit.form.TextBox\" name=\"keywords\" id=\"keywords\" value=\"");
          out.print(UtilMethods.webifyString(searcher.getKeywords()));
          out.write("\" /></dd>\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Assigned-To"));
          out.write(":</dt>\n");
          out.write("\t\t\t\t\t<dd>\n");
          out.write("\t\t\t\t\t<div id=\"container\">\n");
          out.write("\t\t\t\t\t  <div id=\"table-row\">\n");
          out.write("\t\t\t\t\t\t  <div id=\"cell-left\">\n");
          out.write("\t\t\t\t\t\t\t<input type=\"hidden\" id=\"assignedTo\" name=\"assignedTo\" value=\"");
          out.print(myRole.getId() );
          out.write("\" />\n");
          out.write("\t\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t\t\t  <div id=\"cell-right\">\n");
          out.write("\t\t\t\t\t\t\t");
if(isAdministrator) { 
          out.write("\n");
          out.write("\t\t\t\t\t\t\t<input type=\"radio\" dojoType=\"dijit.form.RadioButton\" id=\"showAllLink\" name=\"assignedto\" onclick=\"showTasks4AllUsers()\">");
          out.print(LanguageUtil.get(pageContext, "all") );
          out.write(" </input>\n");
          out.write("\t                        ");
} 
          out.write("\n");
          out.write("\t                      </div>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t\t  <div id=\"table-row\">\n");
          out.write("\t\t\t\t\t  \t <div id=\"cell-left\"> </div>\n");
          out.write("\t\t\t\t\t     <div id=\"cell-right\">\n");
          out.write("                       \t\t <input type=\"radio\" dojoType=\"dijit.form.RadioButton\" id=\"showme\" name=\"assignedto\" checked=\"true\" onclick=\"assignedToMe()\">");
          out.print(LanguageUtil.get(pageContext, "me") );
          out.write("</input>\n");
          out.write("\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t </div>\n");
          out.write("\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t</dd>\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Older_than_(days)") );
          out.write("</dt>\n");
          out.write("\t\t\t\t\t<dd>\n");
          out.write("\t\t\t\t\t   <input type=\"text\" id=\"daysold\" name=\"daysold\"/>\n");
          out.write("\t\t\t\t\t</dd>\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Scheme"));
          out.write(":</dt>\n");
          out.write("\t\t\t\t\t<dd>\n");
          out.write("\t\t\t\t\t\t<select name=\"schemeId\" id=\"schemeId\" dojoType=\"dijit.form.FilteringSelect\" value=\"");
          out.print(UtilMethods.webifyString(searcher.getSchemeId()));
          out.write("\" onChange=\"updateSteps();doFilter();\">\n");
          out.write("\t\t\t\t\t\t\t<option value=\"\"></option>\n");
          out.write("\t\t\t\t\t\t\t");
for(WorkflowScheme scheme : schemes) {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t<option value=\"");
          out.print(scheme.getId());
          out.write('"');
          out.write(' ');
          out.write(' ');
          out.print((scheme.getId().equals(searcher.getSchemeId())) ? "selected": "");
          out.write('>');
          out.print(scheme.getName());
          out.write("</option>\n");
          out.write("\t\t\t\t\t\t\t");
} 
          out.write("\n");
          out.write("\t\t\t\t\t\t</select>\n");
          out.write("\n");
          out.write("\n");
          out.write("\t\t\t\t\t</dd>\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Step"));
          out.write(":</dt>\n");
          out.write("\t\t\t\t\t<dd>\n");
          out.write("\n");
          out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"stepId\" name=\"stepId\"  />\n");
          out.write("\n");
          out.write("\n");
          out.write("\n");
          out.write("\t\t\t\t\t</dd>\n");
          out.write("\n");
          out.write("\t\t\t\t\t<dt>");
          out.print(LanguageUtil.get(pageContext, "Show"));
          out.write(":</dt>\n");
          out.write("\t\t\t\t\t<dd>\n");
          out.write("\t\t\t\t\t\t<input dojoType=\"dijit.form.CheckBox\" ");
if(searcher.isOpen()){
          out.write(" checked='checked' ");
}
          out.write(" type=\"checkbox\" name=\"open\" value=\"true\" id=\"showOpen\" onclick=\"doFilter()\" /> <label for=\"showOpen\">");
          out.print(LanguageUtil.get(pageContext, "open-tasks"));
          out.write("</label><br/>\n");
          out.write("\t\t\t\t\t\t<input dojoType=\"dijit.form.CheckBox\" ");
if(searcher.isClosed()){
          out.write(" checked='checked' ");
}
          out.write(" type=\"checkbox\" name=\"closed\" value=\"true\" id=\"showClosed\"  onclick=\"doFilter()\"  /> <label for=\"showClosed\">");
          out.print(LanguageUtil.get(pageContext, "resolved-tasks"));
          out.write("</label><br/>\n");
          out.write("\t\t\t\t\t</dd>\n");
          out.write("\t\t\t\t</dl>\n");
          out.write("\t\t\t\t<div class=\"buttonRow\">\n");
          out.write("\t\t\t\t\t<button dojoType=\"dijit.form.Button\" iconClass=\"searchIcon\" name=\"filterButton\" onclick=\"doFilter()\"> ");
          out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "Search")) );
          out.write("</button>\n");
          out.write("\t\t\t\t\t<button dojoType=\"dijit.form.Button\" name=\"resetButton\"  iconClass=\"resetIcon\" onclick=\"resetFilters()\">");
          out.print(LanguageUtil.get(pageContext, "reset"));
          out.write("</button>\n");
          out.write("\t\t\t\t</div>\n");
          out.write("\t\t\t</div>\n");
          out.write("\t\t</div>\n");
          out.write("\t</div>\n");
          out.write("<!-- END Left Column -->\n");
          out.write("\n");
          out.write("\n");
          out.write("<!-- START Right Column -->\n");
          out.write("\t<div dojoType=\"dijit.layout.ContentPane\" splitter=\"true\" region=\"center\" style=\"margin-top:37px;\">\n");
          out.write("\t\t<div id=\"hangTaskListHere\">\n");
          out.write("\n");
          out.write("\n");
          out.write("\t\t</div>\n");
          out.write("\n");
          out.write("\n");
          out.write("\t</div>\n");
          out.write("<!-- END Right Column -->\n");
          out.write("\n");
          out.write("</div>\n");
          out.write("<!-- END Split Box -->\n");
          out.write("\n");
          int evalDoAfterBody = _jspx_th_liferay_005fbox_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay_005fbox_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_liferay_005fbox_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom.reuse(_jspx_th_liferay_005fbox_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fliferay_005fbox_0026_005ftop_005fbottom.reuse(_jspx_th_liferay_005fbox_005f0);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("dojo.ready(resizeBrowser);\n");
      out.write("</script>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_portlet_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f0 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f0);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(8,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f0.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(8,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f0.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f0 = _jspx_th_portlet_005fparam_005f0.doStartTag();
    if (_jspx_th_portlet_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f1 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f0);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(9,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f1.setName("cmd");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(9,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f1.setValue("publish");
    int _jspx_eval_portlet_005fparam_005f1 = _jspx_th_portlet_005fparam_005f1.doStartTag();
    if (_jspx_th_portlet_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f3 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f1);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(20,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f3.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(20,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f3.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f3 = _jspx_th_portlet_005fparam_005f3.doStartTag();
    if (_jspx_th_portlet_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f4 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f1);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(21,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f4.setName("cmd");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(21,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f4.setValue("unpublish");
    int _jspx_eval_portlet_005fparam_005f4 = _jspx_th_portlet_005fparam_005f4.doStartTag();
    if (_jspx_th_portlet_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f6 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f2);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(33,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f6.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(33,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f6.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f6 = _jspx_th_portlet_005fparam_005f6.doStartTag();
    if (_jspx_th_portlet_005fparam_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f6);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f7 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f2);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(34,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f7.setName("cmd");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(34,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f7.setValue("delete");
    int _jspx_eval_portlet_005fparam_005f7 = _jspx_th_portlet_005fparam_005f7.doStartTag();
    if (_jspx_th_portlet_005fparam_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f7);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f9 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f3);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(46,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f9.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(46,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f9.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f9 = _jspx_th_portlet_005fparam_005f9.doStartTag();
    if (_jspx_th_portlet_005fparam_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f9);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f10 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f3);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(47,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f10.setName("cmd");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(47,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f10.setValue("undelete");
    int _jspx_eval_portlet_005fparam_005f10 = _jspx_th_portlet_005fparam_005f10.doStartTag();
    if (_jspx_th_portlet_005fparam_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f10);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f12 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f4);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(56,89) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f12.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(56,89) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f12.setValue("/ext/htmlpages/preview_htmlpage");
    int _jspx_eval_portlet_005fparam_005f12 = _jspx_th_portlet_005fparam_005f12.doStartTag();
    if (_jspx_th_portlet_005fparam_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f12);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f13 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f4);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(56,167) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f13.setName("previewPage");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(56,167) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f13.setValue("1");
    int _jspx_eval_portlet_005fparam_005f13 = _jspx_th_portlet_005fparam_005f13.doStartTag();
    if (_jspx_th_portlet_005fparam_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f13);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f14 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f5);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(67,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f14.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(67,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f14.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f14 = _jspx_th_portlet_005fparam_005f14.doStartTag();
    if (_jspx_th_portlet_005fparam_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f14);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f15 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f5);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(68,18) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f15.setName("cmd");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(68,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f15.setValue("full_delete");
    int _jspx_eval_portlet_005fparam_005f15 = _jspx_th_portlet_005fparam_005f15.doStartTag();
    if (_jspx_th_portlet_005fparam_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f15);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f17 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f6);
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(258,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f17.setName("struts_action");
    // /html/portlet/ext/workflows/workflows_js_inc.jsp(258,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f17.setValue("/ext/workflows/view_workflow_tasks");
    int _jspx_eval_portlet_005fparam_005f17 = _jspx_th_portlet_005fparam_005f17.doStartTag();
    if (_jspx_th_portlet_005fparam_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f17);
    return false;
  }

  private boolean _jspx_meth_portlet_005factionURL_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:actionURL
    com.liferay.portlet.taglib.ActionURLTag _jspx_th_portlet_005factionURL_005f7 = (com.liferay.portlet.taglib.ActionURLTag) _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.get(com.liferay.portlet.taglib.ActionURLTag.class);
    _jspx_th_portlet_005factionURL_005f7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005factionURL_005f7.setParent(null);
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,13) name = windowState type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005factionURL_005f7.setWindowState("maximized");
    int _jspx_eval_portlet_005factionURL_005f7 = _jspx_th_portlet_005factionURL_005f7.doStartTag();
    if (_jspx_eval_portlet_005factionURL_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_portlet_005factionURL_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_portlet_005factionURL_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_portlet_005factionURL_005f7.doInitBody();
      }
      do {
        if (_jspx_meth_portlet_005fparam_005f19(_jspx_th_portlet_005factionURL_005f7, _jspx_page_context))
          return true;
        if (_jspx_meth_portlet_005fparam_005f20(_jspx_th_portlet_005factionURL_005f7, _jspx_page_context))
          return true;
        if (_jspx_meth_portlet_005fparam_005f21(_jspx_th_portlet_005factionURL_005f7, _jspx_page_context))
          return true;
        if (_jspx_meth_portlet_005fparam_005f22(_jspx_th_portlet_005factionURL_005f7, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_portlet_005factionURL_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_portlet_005factionURL_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_portlet_005factionURL_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005factionURL_0026_005fwindowState.reuse(_jspx_th_portlet_005factionURL_005f7);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f19 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f7);
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,56) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f19.setName("struts_action");
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,56) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f19.setValue("/ext/workflows/edit_workflow_task");
    int _jspx_eval_portlet_005fparam_005f19 = _jspx_th_portlet_005fparam_005f19.doStartTag();
    if (_jspx_th_portlet_005fparam_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f19);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f20 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f7);
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,136) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f20.setName("cmd");
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,136) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f20.setValue("view");
    int _jspx_eval_portlet_005fparam_005f20 = _jspx_th_portlet_005fparam_005f20.doStartTag();
    if (_jspx_th_portlet_005fparam_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f20);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f21 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f7);
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,177) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f21.setName("taskId");
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,177) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f21.setValue("REPLACEME");
    int _jspx_eval_portlet_005fparam_005f21 = _jspx_th_portlet_005fparam_005f21.doStartTag();
    if (_jspx_th_portlet_005fparam_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f21);
    return false;
  }

  private boolean _jspx_meth_portlet_005fparam_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_005factionURL_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.ParamTag _jspx_th_portlet_005fparam_005f22 = (com.liferay.taglib.ParamTag) _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.get(com.liferay.taglib.ParamTag.class);
    _jspx_th_portlet_005fparam_005f22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_005fparam_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_005factionURL_005f7);
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,226) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f22.setName("language");
    // /html/portlet/ext/workflows/view_workflow_tasks.jsp(350,226) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_portlet_005fparam_005f22.setValue("LANGUAGE");
    int _jspx_eval_portlet_005fparam_005f22 = _jspx_th_portlet_005fparam_005f22.doStartTag();
    if (_jspx_th_portlet_005fparam_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fportlet_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_portlet_005fparam_005f22);
    return false;
  }
}
