package org.apache.jsp.html.portal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.dotmarketing.util.Parameter;
import com.dotmarketing.business.Role;
import com.dotmarketing.business.APILocator;
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
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortletActiveException;
import com.liferay.portal.RequiredLayoutException;
import com.liferay.portal.RequiredRoleException;
import com.liferay.portal.SendPasswordException;
import com.liferay.portal.UserActiveException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.auth.AuthException;
import com.liferay.portal.struts.PortletRequestProcessor;
import com.liferay.portal.util.PortletTitleComparator;
import javax.servlet.jsp.PageContext;
import org.apache.commons.fileupload.LiferayDiskFileUpload;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.TilesUtil;
import com.liferay.portal.UserActiveException;
import com.dotmarketing.business.APILocator;

public final class layout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(10);
    _jspx_dependants.add("/html/portal/layout_init.jsp");
    _jspx_dependants.add("/html/portal/init.jsp");
    _jspx_dependants.add("/html/common/init.jsp");
    _jspx_dependants.add("/html/portal/view_portlet_inc.jsp");
    _jspx_dependants.add("/WEB-INF/tld/c-rt.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-portlet.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-tiles.tld");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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






	Portlet[] portlets = null;
	
	List userRoles = APILocator.getRoleAPI().loadRolesForUser(user.getUserId());




//boolean statePopUp = ParamUtil.getString(request, "p_p_state").equals(LiferayWindowState.POP_UP.toString()) ? true : false;
boolean statePopUp = true;
boolean child = ParamUtil.getString(request, "child").equals("true") ? true : false;


	String curColumnOrder = "w";
	portlets = new Portlet[0];
	int j = 0;

//	Portlet portlet = PortletManagerUtil.getPortletById(company.getCompanyId(), layout.getPortletIds().get(0));
Portlet portlet = PortletManagerUtil.getPortletById(company.getCompanyId(), ParamUtil.getString(request,"p_p_id", layout.getPortletIds().get(0)));




boolean access = APILocator.getLayoutAPI().doesUserHaveAccessToPortlet(portlet.getPortletId(),user);
String licenseManagerOverrideTicket = (String)request.getParameter("licenseManagerOverrideTicket");
String roleAdminOverrideTicket = (String)request.getParameter("roleAdminOverrideTicket");
if(roleAdminOverrideTicket!=null){
	if(request.getSession().getAttribute("roleAdminOverrideTicket")!=null){
	  String overrideTicket = (String)request.getSession().getAttribute("roleAdminOverrideTicket");
	   if(overrideTicket!=null && roleAdminOverrideTicket.equalsIgnoreCase(overrideTicket)){
		  access = true;
	   }
	}
}

if(licenseManagerOverrideTicket!=null){
	if(request.getSession().getAttribute("licenseManagerOverrideTicket")!=null){
	  String overrideTicket = (String)request.getSession().getAttribute("licenseManagerOverrideTicket");
	   if(overrideTicket!=null && licenseManagerOverrideTicket.equalsIgnoreCase(overrideTicket)){
		  access = true;
	   }
	}
}


CachePortlet cachePortlet = null;
try {
	cachePortlet = PortalUtil.getPortletInstance(portlet, application);
}
/*catch (UnavailableException ue) {
	ue.printStackTrace();
}*/
catch (PortletException pe) {
	pe.printStackTrace();
}
catch (RuntimeException re) {
	re.printStackTrace();
}

//PortletPreferences portletPrefs = PortletPreferencesManagerUtil.getPreferences(company.getCompanyId(), PortalUtil.getPortletPreferencesPK(request, portlet.getPortletId()));
PortletPreferences portletPrefs = null;

PortletConfig portletConfig = PortalUtil.getPortletConfig(portlet, application);
PortletContext portletCtx = portletConfig.getPortletContext();


// Passing in a dynamic request with box_width makes it easier to render
// portlets, but will break the TCK because the request parameters will have an
// extra parameter


RenderRequestImpl renderRequest = new RenderRequestImpl(request, portlet, cachePortlet, portletCtx, null, null, portletPrefs, layoutId);

StringServletResponse stringServletRes = new StringServletResponse(response);

RenderResponseImpl renderResponse = new RenderResponseImpl(renderRequest, stringServletRes, portlet.getPortletId(), company.getCompanyId(), layoutId);

renderRequest.defineObjects(portletConfig, renderResponse);


int portletTitleLength = 12;

Map portletViewMap = CollectionFactory.getHashMap();

portletViewMap.put("access", new Boolean(access));
portletViewMap.put("active", new Boolean(portlet.isActive()));

portletViewMap.put("portletId", portlet.getPortletId());
portletViewMap.put("portletTitleLength", new Integer(portletTitleLength));

portletViewMap.put("restoreCurrentView", new Boolean(portlet.isRestoreCurrentView()));

renderRequest.setAttribute(WebKeys.PORTLET_VIEW_MAP, portletViewMap);

if ((cachePortlet != null) && cachePortlet.isStrutsPortlet()) {

	// Make sure the Tiles context is reset for the next portlet

	request.removeAttribute(org.apache.struts.taglib.tiles.ComponentConstants.COMPONENT_CONTEXT);
}

boolean portletException = false;

if (portlet.isActive() && access) {
	try {
		cachePortlet.render(renderRequest, renderResponse);
	}
	catch (UnavailableException ue) {
		portletException = true;

		PortalUtil.destroyPortletInstance(portlet);
	}
	catch (Exception e) {
		portletException = true;

		e.printStackTrace();
	}

	SessionMessages.clear(renderRequest);
	SessionErrors.clear(renderRequest);
}

boolean showPortletAccessDenied = portlet.isShowPortletAccessDenied();
boolean showPortletInactive = portlet.isShowPortletInactive();

	if ((cachePortlet != null) && cachePortlet.isStrutsPortlet()) {
		if (!access || portletException) {
			PortletRequestProcessor portletReqProcessor = (PortletRequestProcessor)portletCtx.getAttribute(WebKeys.PORTLET_STRUTS_PROCESSOR);

			ActionMapping actionMapping = portletReqProcessor.processMapping(request, response, (String)portlet.getInitParams().get("view-action"));

			ComponentDefinition definition = null;

			if (actionMapping != null) {

				// See action path /weather/view

				String definitionName = actionMapping.getForward();

				if (definitionName == null) {

					// See action path /journal/view_articles

					String[] definitionNames = actionMapping.findForwards();

					for (int definitionNamesPos = 0; definitionNamesPos < definitionNames.length; definitionNamesPos++) {
						if (definitionNames[definitionNamesPos].endsWith("view")) {
							definitionName = definitionNames[definitionNamesPos];

							break;
						}
					}

					if (definitionName == null) {
						definitionName = definitionNames[0];
					}
				}

				definition = TilesUtil.getDefinition(definitionName, request, application);
			}

			String templatePath = Constants.TEXT_HTML_DIR + "/portal/layout_portal.jsp";
			if (definition != null) {
				templatePath = Constants.TEXT_HTML_DIR + definition.getPath();
			}
	
      out.write("\r\n");
      out.write("\t\t\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/html/portal/portlet_error.jsp", out, false);
      out.write('\r');
      out.write('\n');
      out.write('	');

		}
		else {

				pageContext.getOut().print(stringServletRes.getString());

		}
	}
	else {
		renderRequest.setAttribute(WebKeys.PORTLET_CONTENT, stringServletRes.getString());

		String portletContent = StringPool.BLANK;
		if (portletException) {
			portletContent = "/portal/portlet_error.jsp";
		}
		
		
if(!statePopUp || portletException){
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/html/portal/portlet_error.jsp", out, false);
      out.write('\r');
      out.write('\n');
}else{ 
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.print( renderRequest.getAttribute(WebKeys.PORTLET_CONTENT) );
      out.write('\r');
      out.write('\n');
}}
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar shown=false;\r\n");
      out.write("\tfunction showHelp() {\r\n");
      out.write("\t\tvar helpUrl = \"//dotcms.com/inline-help/2.0/");
      out.print(portlet.getPortletId() );
      out.write("\";\r\n");
      out.write("        require([\"dojo/_base/fx\", \"dojo/dom\", \"dojo/window\", \"dojo/dom-construct\",\"dojo/dom-style\"], function(baseFx, dom, win, cons, dstyle) {\r\n");
      out.write("        \tvar vp=win.getBox(win.doc);\r\n");
      out.write("        \tif(!shown) {\r\n");
      out.write("        \t    shown=true;\r\n");
      out.write("        \t\tcons.create(\"iframe\",{id:\"helpiframe\",src:helpUrl,width:'600', height:vp.h-60, style:'border: 0 none;' },\"helpcontent\");\r\n");
      out.write("\t        \tbaseFx.animateProperty({\r\n");
      out.write("\t                node: dom.byId(\"helpId\"),\r\n");
      out.write("\t                duration: 600,\r\n");
      out.write("\t                properties: { top: {start:vp.h-30,end:20 } }\r\n");
      out.write("\t            }).play();\r\n");
      out.write("        \t}\r\n");
      out.write("        \telse {\r\n");
      out.write("        \t\tshown=false;\r\n");
      out.write("        \t\tbaseFx.animateProperty({\r\n");
      out.write("                    node: dom.byId(\"helpId\"),\r\n");
      out.write("                    duration: 600,\r\n");
      out.write("                    onEnd:function() {\r\n");
      out.write("                        cons.destroy('helpiframe');\r\n");
      out.write("                        dstyle.set(dom.byId('helpId'),\"top\",\"\");\r\n");
      out.write("                        dstyle.set(dom.byId('helpId'),\"bottom\",\"-1px\");\r\n");
      out.write("                    },\r\n");
      out.write("                    properties: { top: {end:vp.h-30,start:20 } }\r\n");
      out.write("                }).play();\r\n");
      out.write("        \t\t\r\n");
      out.write("        \t}\r\n");
      out.write("        });\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
if(request.getParameter("in_frame")==null){
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"helpId\" id=\"helpId\">\r\n");
      out.write("\t<a href=\"#\" onclick=\"showHelp();\" class=\"dotcmsHelpButton\">");
      out.print(LanguageUtil.get(pageContext, "help") );
      out.write("</a>\r\n");
      out.write("\t<div id=\"helpcontent\"></div>\r\n");
      out.write("</div>\r\n");
} 
      out.write("\r\n");
      out.write("\r\n");
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
}
