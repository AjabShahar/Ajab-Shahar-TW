package org.apache.jsp.html.portlet.ext.workflows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
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
import com.dotmarketing.beans.Host;
import java.util.List;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.business.PermissionAPI;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.contentlet.business.HostAPI;
import com.liferay.portal.model.User;
import com.liferay.portal.language.LanguageUtil;
import com.dotmarketing.business.UserAPI;
import com.dotmarketing.business.web.HostWebAPI;
import com.dotmarketing.business.web.WebAPILocator;
import com.dotmarketing.cache.VirtualLinksCache;
import com.dotmarketing.util.Config;
import com.dotmarketing.util.URLUtils;
import com.dotmarketing.util.URLUtils.ParsedURL;
import com.dotmarketing.cache.WorkingCache;
import com.liferay.util.ParamUtil;
import com.dotmarketing.portlets.common.bean.CrumbTrailEntry;
import java.util.ArrayList;

public final class sub_005fnav_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(9);
    _jspx_dependants.add("/html/portlet/ext/workflows/init.jsp");
    _jspx_dependants.add("/html/common/init.jsp");
    _jspx_dependants.add("/html/portlet/ext/common/sub_nav_inc.jsp");
    _jspx_dependants.add("/WEB-INF/tld/c-rt.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-portlet.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-tiles.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fportlet_005fdefineObjects_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");

	RenderRequestImpl rreq = (RenderRequestImpl) pageContext.getAttribute("renderRequest");
	String portletId1 = rreq.getPortletName();
	
	if (!UtilMethods.isSet(portletId1))
		portletId1 = layouts[0].getPortletIds().get(0);
	
	Portlet portlet1 = PortletManagerUtil.getPortletById(company.getCompanyId(), portletId1);
	String strutsAction = ParamUtil.get(request, "struts_action", null);
	
	if (!UtilMethods.isSet(strutsAction) || strutsAction.equals(portlet1.getInitParams().get("view-action"))) {
		List<CrumbTrailEntry> crumbTrailEntries = new ArrayList<CrumbTrailEntry>();
		
		crumbTrailEntries.add(new CrumbTrailEntry(LanguageUtil.get(pageContext, "javax.portlet.title." + portletId1), null));
		
		request.setAttribute(com.dotmarketing.util.WebKeys.CMS_CRUMBTRAIL_OPTIONS, crumbTrailEntries);
	} else {
		List<CrumbTrailEntry> crumbTrailEntries = new ArrayList<CrumbTrailEntry>();
		
		crumbTrailEntries.add(new CrumbTrailEntry(LanguageUtil.get(pageContext, "javax.portlet.title." + portletId1), "javascript: cancel();"));
		
		crumbTrailEntries.add(new CrumbTrailEntry(LanguageUtil.get(pageContext, "Add-Edit-Task"), null));
		
		request.setAttribute(com.dotmarketing.util.WebKeys.CMS_CRUMBTRAIL_OPTIONS, crumbTrailEntries);
	}
	
	request.setAttribute(com.dotmarketing.util.WebKeys.DONT_DISPLAY_SUBNAV_ALL_HOSTS, false);


      out.write("\r\n");
      out.write("\r\n");
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


	boolean inPopupIFrame = UtilMethods.isSet(ParamUtil.getString(request, "popup")) || UtilMethods.isSet(ParamUtil.getString(request, "in_frame"));

	if(!inPopupIFrame) {
		UserAPI userAPI = APILocator.getUserAPI();
		HostWebAPI hostApi = WebAPILocator.getHostWebAPI();
	
		Boolean dontDisplayAllHostsOption = (Boolean) request
				.getAttribute(com.dotmarketing.util.WebKeys.DONT_DISPLAY_SUBNAV_ALL_HOSTS);
		if (dontDisplayAllHostsOption == null) {
			dontDisplayAllHostsOption = false;
		}
	
		boolean showHostSelector = request.getAttribute("SHOW_HOST_SELECTOR") != null;
		String hostId = (String)request.getAttribute("_crumbHost");
	
		List<CrumbTrailEntry> crumbTrailEntries = (request
				.getAttribute(com.dotmarketing.util.WebKeys.CMS_CRUMBTRAIL_OPTIONS) != null) ? (List<CrumbTrailEntry>) request
				.getAttribute(com.dotmarketing.util.WebKeys.CMS_CRUMBTRAIL_OPTIONS)
				: new ArrayList<CrumbTrailEntry>();
				
	
	    if(!UtilMethods.isSet(hostId)){
		   hostId = (String) session.getAttribute(com.dotmarketing.util.WebKeys.CMS_SELECTED_HOST_ID);
	    }
		Host currentHost = null;
		String hostName = null;
	
		try{
			currentHost = hostApi.find(hostId, user, false);
			hostName = currentHost.getTitle();
		}
		catch(Exception e){
			try{
				currentHost=hostApi.findDefaultHost(user, false);
				hostName = currentHost.getTitle();
			}
			catch(Exception ex){
				com.dotmarketing.util.Logger.error(this.getClass(), "user does not have a default host");
			}
		}
		
	
	
		String _browserCrumbUrl = null;
		boolean canManageHosts = false;
		String _hostManagerUrl = null;
	
		// if we have a host, get the url for the browser
		for (int i = 0; i < layouts.length; i++) {
			List<String> portletIDs = layouts[i].getPortletIds();
			for (String x : portletIDs) {
				if ("EXT_BROWSER".equals(x)) {
					_browserCrumbUrl = new PortletURLImpl(request, x, layouts[i].getId(), false).toString();
				}
				if ("EXT_HOSTADMIN".equals(x)) {
					canManageHosts = true;
					_hostManagerUrl = new PortletURLImpl(request, x, layouts[i].getId(), false).toString();
				}
			}
		}
	
	
	
	 if (showHostSelector) {

      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("        dojo.require('dotcms.dojo.data.HostReadStore');\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<!-- START Pop-up Host Select -->\n");
      out.write("<div id=\"hostSelectDialog\" style=\"visibility: hidden; display: none;\"\n");
      out.write("\ttitle=\"");
      out.print( LanguageUtil.get(pageContext, "select-host") );
      out.write('"');
      out.write('\n');
      out.write('>');
      out.print(LanguageUtil.get(pageContext, "select-host-nice-message"));
      out.write("\n");
      out.write("<span dojoType=\"dotcms.dojo.data.HostReadStore\" jsId=\"HostStore\"></span>\n");
      out.write("<div style=\"text-align: center; padding: 15px;\">\n");
      out.write("<div class=\"selectHostIcon\"></div>\n");
      out.write("<select id=\"subNavHost\" name=\"subNavHost\" dojoType=\"dijit.form.FilteringSelect\"\n");
      out.write("\tstore=\"HostStore\"  pageSize=\"30\" labelAttr=\"hostname\"  searchAttr=\"hostname\" \n");
      out.write("\tsearchDelay=\"400\" invalidMessage=\"");
      out.print( LanguageUtil.get(pageContext, "Invalid-option-selected"));
      out.write("\"\n");
      out.write("\tonchange=\"updateCMSSelectedHosts()\"\n");
      out.write("\t>\n");
      out.write("</select>\n");
      out.write("</div>\n");
      out.write("<div style=\"text-align: right;\">\n");

	if (canManageHosts) {

      out.write("\n");
      out.write("<div\n");
      out.write("\tstyle=\"float: left; font-size: 85%; padding-top: 7px; font-style: italic\"\n");
      out.write("><a href=\"");
      out.print(_hostManagerUrl );
      out.write('"');
      out.write('>');
      out.print(LanguageUtil.get(pageContext, "manage-hosts"));
      out.write("</a>\n");
      out.write("</div>\n");

	}

      out.write("\n");
      out.write("<button dojoType=\"dijit.form.Button\"\n");
      out.write("\tonClick=\"dijit.popup.close(myDialog);\" iconClass=\"cancelIcon\"\n");
      out.write(">");
      out.print(LanguageUtil.get(pageContext, "cancel"));
      out.write("</button>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t\tvar myDialog = new dijit.TooltipDialog({style:'display:none;'}, \"hostSelectDialog\");\n");
      out.write("\t\tmyDialog.startup();\n");
      out.write("\t</script>\n");
      out.write("<!-- END Pop-up Host Select -->\n");

	}

      out.write('\n');
      out.write('\n');

	if (0 < crumbTrailEntries.size()) {

      out.write('\n');

	boolean _amITheFirst = true;

      out.write("\n");
      out.write("<div class=\"subNavCrumbTrail\" id=\"subNavCrumbTrail\">\n");
      out.write("<ul id=\"ulNav\">\n");
      out.write("\t");
 if (!showHostSelector) { 
      out.write('\n');
      out.write('	');
 _amITheFirst = false; 
      out.write("\n");
      out.write("\t\t<li id=\"selectHostDiv\" style=\"\"\n");
      out.write("\t\t\t");
if(UtilMethods.isSet(_browserCrumbUrl)){ 
      out.write("\n");
      out.write("\t\t\t\tonclick=\"window.location='");
      out.print(_browserCrumbUrl);
      out.write("';\" \n");
      out.write("\t\t\t");
} 
      out.write("\n");
      out.write("\t\t>\n");
      out.write("\t\t\t<span class=\"hostStoppedIcon\" style=\"float:left;margin-right:5px;\"></span>\n");
      out.write("\t\t\t");
      out.print(LanguageUtil.get(pageContext, "Global-Page"));
      out.write("\n");
      out.write("\t\t</li>\n");
      out.write("\t");
 } 
      out.write('\n');
      out.write('\n');
      out.write('	');
 for (CrumbTrailEntry crumbTrailEntry : crumbTrailEntries) { 
      out.write('\n');
      out.write('	');
 if (UtilMethods.isSet(crumbTrailEntry.getLink())) { 
      out.write("\n");
      out.write("\t\t<li style=\"cursor: pointer\" \n");
      out.write("\t\t\t");
if(_amITheFirst){
      out.write(" id=\"selectHostDiv\"");
} 
      out.write("\n");
      out.write("\t\t>\n");
      out.write("\t\t\t");
 if (_amITheFirst) { 
      out.write(" \n");
      out.write("\t\t\t\t<span class=\"publishIcon\"></span> \n");
      out.write("\t\t\t");
 } 
      out.write(" \n");
      out.write("\t\t\t");
 _amITheFirst = false; 
      out.write(" \n");
      out.write("\t\t\t<a href=\"\n");
      out.write("\t\t\t\t");
      out.print( crumbTrailEntry.getLink() );
      out.write("\"\n");
      out.write("\t\t\t>\n");
      out.write("\t\t\t\t");
      out.print(crumbTrailEntry.getTitle());
      out.write("\n");
      out.write("\t\t\t</a>\n");
      out.write("\t\t</li>\n");
      out.write("\t");

		} else {
	
      out.write("\n");
      out.write("\t<li class=\"lastCrumb\" id=\"lastCrumb\"><span>");
      out.print(crumbTrailEntry.getTitle());
      out.write("</span></li>\n");
      out.write("\t");

		}
	
      out.write('\n');
      out.write('	');

		}
	
      out.write("\n");
      out.write("</ul>\n");

	if (showHostSelector) {

      out.write("\n");
      out.write("<div class=\"changeHost\" onclick=\"dijit.popup.open({popup: myDialog, around: dojo.byId('changeHostId')})\">\n");
      out.write("\t<span id=\"changeHostId\">");
      out.print(LanguageUtil.get(pageContext, "Change-Host"));
      out.write("</span>\n");
      out.write("\t<span class=\"chevronExpandIcon\"></span>\n");
      out.write("</div>\n");

	}

      out.write("\n");
      out.write("<div class=\"clear\"></div>\n");
      out.write("\n");
      out.write("</div>\n");

	}

      out.write("\n");
      out.write("<div class=\"clear\"></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t\t\n");
      out.write("\n");
      out.write("\tfunction showHostPreview() {\n");
      out.write("\t\twindow.location = '");
      out.print(_browserCrumbUrl);
      out.write("';\n");
      out.write("\t}\n");
      out.write("\tfunction updateCMSSelectedHosts() {\n");
      out.write("\t\tif( dijit.byId('subNavHost').attr('value')!=null && dijit.byId('subNavHost').attr('value')!=''){\n");
      out.write("\t\t\twindow.location.href = \"/html/portlet/ext/common/sub_nav_refresh_host.jsp?referer=\" + escape(window.location) + \"&host_id=\" + dijit.byId('subNavHost').attr('value');\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("</script>\n");

	}

      out.write('\n');
      out.write('\n');
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
