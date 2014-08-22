package org.apache.jsp.html.portal;

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
import com.dotmarketing.util.Config;
import com.dotmarketing.util.UtilMethods;
import org.apache.struts.action.ActionErrors;
import java.util.HashSet;
import java.util.Set;
import org.apache.struts.action.ActionErrors;
import com.liferay.portal.language.LanguageUtil;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts.action.ActionMessage;
import java.util.Iterator;
import com.liferay.util.servlet.SessionMessages;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.Globals;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.Config;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.model.Portlet;
import com.dotcms.rest.WebResource;
import com.dotmarketing.business.APILocator;
import java.util.List;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLImpl;
import javax.portlet.WindowState;
import javax.portlet.PortletMode;
import com.liferay.portal.util.PortletKeys;
import javax.portlet.PortletURL;
import com.liferay.portal.language.LanguageUtil;
import com.dotmarketing.business.Role;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.util.CompanyUtils;
import com.dotmarketing.util.Config;
import com.liferay.portal.util.ReleaseInfo;
import java.net.URLEncoder;
import com.dotmarketing.viewtools.JSONTool;
import com.dotmarketing.util.json.JSONObject;
import com.dotmarketing.util.json.JSONArray;
import java.util.Date;
import java.util.Calendar;
import com.dotmarketing.business.Layout;
import java.util.List;
import com.liferay.portal.util.WebKeys;
import com.dotmarketing.util.UtilMethods;
import org.apache.struts.action.ActionErrors;
import java.util.HashSet;
import java.util.Set;
import org.apache.struts.action.ActionErrors;
import com.liferay.portal.language.LanguageUtil;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts.action.ActionMessage;
import java.util.Iterator;
import com.liferay.util.servlet.SessionMessages;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.Globals;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ParamUtil;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.Config;
import com.liferay.portal.language.LanguageUtil;
import com.dotmarketing.db.DbConnectionFactory;

public final class layout_005fportal_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(13);
    _jspx_dependants.add("/html/common/init.jsp");
    _jspx_dependants.add("/html/common/top_inc.jsp");
    _jspx_dependants.add("/html/common/messages_inc.jsp");
    _jspx_dependants.add("/html/common/bottom_inc.jsp");
    _jspx_dependants.add("/html/common/nav_main_inc.jsp");
    _jspx_dependants.add("/html/common/nav_sub_inc.jsp");
    _jspx_dependants.add("/html/common/bottom_portal_inc.jsp");
    _jspx_dependants.add("/WEB-INF/tld/c-rt.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-portlet.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
    _jspx_dependants.add("/WEB-INF/tld/struts-tiles.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody.release();
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.release();
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
      //  tiles:useAttribute
      org.apache.struts.taglib.tiles.UseAttributeTag _jspx_th_tiles_005fuseAttribute_005f0 = (org.apache.struts.taglib.tiles.UseAttributeTag) _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.get(org.apache.struts.taglib.tiles.UseAttributeTag.class);
      _jspx_th_tiles_005fuseAttribute_005f0.setPageContext(_jspx_page_context);
      _jspx_th_tiles_005fuseAttribute_005f0.setParent(null);
      // /html/portal/layout_portal.jsp(2,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f0.setId("tilesContent");
      // /html/portal/layout_portal.jsp(2,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f0.setName("content");
      // /html/portal/layout_portal.jsp(2,0) name = classname type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f0.setClassname("java.lang.String");
      int _jspx_eval_tiles_005fuseAttribute_005f0 = _jspx_th_tiles_005fuseAttribute_005f0.doStartTag();
      if (_jspx_th_tiles_005fuseAttribute_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.reuse(_jspx_th_tiles_005fuseAttribute_005f0);
        return;
      }
      _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.reuse(_jspx_th_tiles_005fuseAttribute_005f0);
      java.lang.String tilesContent = null;
      tilesContent = (java.lang.String) _jspx_page_context.findAttribute("tilesContent");
      out.write('\n');
      //  tiles:useAttribute
      org.apache.struts.taglib.tiles.UseAttributeTag _jspx_th_tiles_005fuseAttribute_005f1 = (org.apache.struts.taglib.tiles.UseAttributeTag) _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.get(org.apache.struts.taglib.tiles.UseAttributeTag.class);
      _jspx_th_tiles_005fuseAttribute_005f1.setPageContext(_jspx_page_context);
      _jspx_th_tiles_005fuseAttribute_005f1.setParent(null);
      // /html/portal/layout_portal.jsp(3,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f1.setId("tilesPortletSubNav");
      // /html/portal/layout_portal.jsp(3,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f1.setName("portlet_sub_nav");
      // /html/portal/layout_portal.jsp(3,0) name = classname type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_tiles_005fuseAttribute_005f1.setClassname("java.lang.String");
      int _jspx_eval_tiles_005fuseAttribute_005f1 = _jspx_th_tiles_005fuseAttribute_005f1.doStartTag();
      if (_jspx_th_tiles_005fuseAttribute_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.reuse(_jspx_th_tiles_005fuseAttribute_005f1);
        return;
      }
      _005fjspx_005ftagPool_005ftiles_005fuseAttribute_0026_005fname_005fid_005fclassname_005fnobody.reuse(_jspx_th_tiles_005fuseAttribute_005f1);
      java.lang.String tilesPortletSubNav = null;
      tilesPortletSubNav = (java.lang.String) _jspx_page_context.findAttribute("tilesPortletSubNav");
      out.write('\n');

        boolean inPortal = (request.getAttribute("org.dotcms.variables.inPortlets") != null);
        boolean inPopupIFrame = UtilMethods.isSet(ParamUtil.getString(request, "popup")) || UtilMethods.isSet(ParamUtil.getString(request, "in_frame"));

        request.setAttribute("org.dotcms.variables.inPortlets", "true"); 
        

      out.write('\n');
      out.write('\n');
if(inPortal ) {
      out.write("\n");
      out.write("        ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /html/portal/layout_portal.jsp(13,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest( Validator.isNotNull(tilesPortletSubNav) );
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                <div class=\"portlet-wrapper\" >\n");
          out.write("                        ");
          //  liferay:include
          com.liferay.taglib.IncludeTag _jspx_th_liferay_005finclude_005f0 = (com.liferay.taglib.IncludeTag) _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody.get(com.liferay.taglib.IncludeTag.class);
          _jspx_th_liferay_005finclude_005f0.setPageContext(_jspx_page_context);
          _jspx_th_liferay_005finclude_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          // /html/portal/layout_portal.jsp(15,24) name = page type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_liferay_005finclude_005f0.setPage( Constants.TEXT_HTML_DIR + tilesPortletSubNav );
          // /html/portal/layout_portal.jsp(15,24) name = flush type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_liferay_005finclude_005f0.setFlush(true);
          int _jspx_eval_liferay_005finclude_005f0 = _jspx_th_liferay_005finclude_005f0.doStartTag();
          if (_jspx_th_liferay_005finclude_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody.reuse(_jspx_th_liferay_005finclude_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fliferay_005finclude_0026_005fpage_005fflush_005fnobody.reuse(_jspx_th_liferay_005finclude_005f0);
          out.write("\n");
          out.write("                </div>\n");
          out.write("        ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      out.write("\n");
      out.write("        <div class=\"portlet-wrapper\" >\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  Constants.TEXT_HTML_DIR + tilesContent , out, false);
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
}else if(inPopupIFrame) { 
      out.write("\n");
      out.write("        ");
      out.write('\n');
      out.write('\n');
      out.write('\n');


	String dojoPath = Config.getStringProperty("path.to.dojo");
	if(!UtilMethods.isSet(dojoPath)){
		// Change dojopath in dotmarketing-config.properties!
		response.sendError(500, "No dojo path variable (path.to.dojo) set in the property file");
	}
	String agent = request.getHeader("User-Agent");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","01 Jan 2000 00:00:00 GMT");


      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:bi=\"urn:bi\" xmlns:csp=\"urn:csp\">\n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"x-ua-compatible\" content=\"IE=edge\" >\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("\t\n");
      out.write("\t<meta content=\"no-cache\" http-equiv=\"Cache-Control\" />\n");
      out.write("\t<meta content=\"no-cache\" http-equiv=\"Pragma\" />\n");
      out.write("\t<meta content=\"0\" http-equiv=\"Expires\" />\n");
      out.write("\t<meta name=\"Expire\" content=\"Now\" />\n");
      out.write("\t\n");
      out.write("\t<link rel=\"shortcut icon\" href=\"//www.dotcms.com/global/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("\t<title>");
      out.print( DOTCMS_PORTAL_PAGE_TITLE );
      out.write("</title>\n");
      out.write("\t \n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t@import \"/html/common/css.jsp?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"; \n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dijit/themes/dmundra/dmundra.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dijit/themes/dmundra/Grid.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dojox/widget/Calendar/Calendar.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"/html/js/dotcms/dijit/image/image_tools.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("\n");
      out.write("    </style>\n");
      out.write("\t\n");
      out.write("\t<!--[if IE]>\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/html/css/iehacks.css\" />\n");
      out.write("\t<![endif]--> \n");
      out.write("    \n");
      out.write("    ");

    String dojoLocaleConfig = "locale:'en-us'";    
    if(locale != null){
    	dojoLocaleConfig = "locale:'"+locale.getLanguage() + "-" + locale.getCountry().toLowerCase() + "',";    	
    }    
    
      out.write("\n");
      out.write("   \t    \n");
      out.write("   \t<script type=\"text/javascript\">\n");
      out.write("       \tdjConfig={\n");
      out.write("               parseOnLoad: true,\n");
      out.write("               useXDomain: false,\n");
      out.write("               isDebug: false,\n");
      out.write("               ");
      out.print(dojoLocaleConfig);
      out.write("\n");
      out.write("               modulePaths: { dotcms: \"/html/js/dotcms\" }\n");
      out.write("       };\n");
      out.write("\t   \n");
      out.write("\t   \tfunction isInodeSet(x){\n");
      out.write("\t\t\treturn (x && x != undefined && x!=\"\" && x.length>15);\n");
      out.write("\t\t}\n");
      out.write("   \t</script>\n");
      out.write("\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(dojoPath);
      out.write("/dojo/dojo.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/html/common/javascript.jsp?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/engine.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/util.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/TemplateAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/HostAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/ContainerAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/RoleAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/BrowserAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/UserAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/InodeAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("\t\tdojo.require(\"dijit.Dialog\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Button\");\n");
      out.write("\t\tdojo.require(\"dijit.form.CheckBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.DateTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.FilteringSelect\");\n");
      out.write("\t\tdojo.require(\"dijit.form.TextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.ValidationTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Textarea\");\n");
      out.write("\t\tdojo.require(\"dijit.Menu\");\n");
      out.write("\t\tdojo.require(\"dijit.MenuItem\");\n");
      out.write("\t\tdojo.require(\"dijit.MenuSeparator\");\n");
      out.write("\t\tdojo.require(\"dijit.ProgressBar\");\n");
      out.write("\t\tdojo.require(\"dijit.PopupMenuItem\");\n");
      out.write("\t\tdojo.require('dijit.layout.TabContainer');\n");
      out.write("\t\tdojo.require('dijit.layout.ContentPane');\n");
      out.write("\t\tdojo.require('dojox.layout.ContentPane');\n");
      out.write("\t\tdojo.require(\"dijit.layout.BorderContainer\");\n");
      out.write("\t\tdojo.require(\"dijit.TitlePane\");\n");
      out.write("\t\tdojo.require(\"dijit.Tooltip\");\n");
      out.write("\t\tdojo.require(\"dojo.parser\");\n");
      out.write("\t\tdojo.require(\"dojo.fx\");\n");
      out.write("\t\tdojo.require(\"dotcms.dojo.data.UsersReadStore\");\n");
      out.write("\t\tdojo.require(\"dojox.form.DropDownSelect\");\n");
      out.write("\t\tdojo.require(\"dojox.json.query\");\n");
      out.write("\t\tdojo.require(\"dijit.form.NumberTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.TimeTextBox\");\n");
      out.write("\t\tdojo.require(\"dotcms.dijit.image.ImageEditor\");\n");
      out.write("\t\tdojo.require(\"dojox.widget.Calendar\");\n");
      out.write("\t\tdojo.require(\"dojo.date.locale\");\n");
      out.write("\t\tdojo.require(\"dojox.form.Uploader\");\n");
      out.write("        dojo.require(\"dojox.form.uploader.FileList\");\n");
      out.write("        dojo.require(\"dojox.form.uploader.plugins.HTML5\");\n");
      out.write("        dojo.require(\"dijit.form.ComboBox\");\n");
      out.write("   \t\tdojo.require(\"dijit.TooltipDialog\");\n");
      out.write("\t\tdojo.require(\"dojox.grid.EnhancedGrid\");\n");
      out.write("\t\tdojo.require(\"dojox.grid.enhanced.plugins.Menu\");\n");
      out.write("\t\tdojo.require(\"dojo.data.ItemFileReadStore\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Form\");\n");
      out.write("\t\tdojo.require(\"dojo.io.script\");\n");
      out.write("\t\t\n");
      out.write("\t\tdojo.addOnLoad(function () {\n");
      out.write("\t\t\tdojo.global.DWRUtil = dwr.util;\n");
      out.write("\t\t\tdojo.global.DWREngine = dwr.engine;\n");
      out.write("\t\t\tdwr.engine.setErrorHandler(DWRErrorHandler);\n");
      out.write("\t\t\tdwr.engine.setWarningHandler(DWRErrorHandler);\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\tfunction DWRErrorHandler(msg, e) {\n");
      out.write("\t\t\tconsole.log(msg, e);\n");
      out.write("\t\t}\n");
      out.write("\t\tvar dojoDom=dojo.require(\"dojo.dom\");\n");
      out.write("\t\tvar dojoDomGeometry=dojo.require(\"dojo.dom-geometry\");\n");
      out.write("\t\tvar dojoStyle=dojo.require(\"dojo.dom-style\");\n");
      out.write("\t\tdojo.coords = function(elem,xx) {\n");
      out.write("            var mb=dojoDomGeometry.getMarginBox(elem,dojoStyle.getComputedStyle(elem));\n");
      out.write("            var abs=dojoDomGeometry.position(elem,xx);\n");
      out.write("            mb.x=abs.x;\n");
      out.write("            mb.y=abs.y;\n");
      out.write("            mb.w=abs.w;\n");
      out.write("            mb.h=abs.h;\n");
      out.write("            return mb;\n");
      out.write("        };\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\t");
 String dotBackImage = (!UtilMethods.isSet(company.getHomeURL()) || "localhost".equals(company.getHomeURL())) ? "/html/images/backgrounds/bg-3.jpg" : company.getHomeURL();
      out.write("\n");
      out.write("\t<style>\n");
      out.write("\t\t.imageBG{background-color:");
      out.print( company.getSize() );
      out.write(";background-image:url(");
      out.print( dotBackImage );
      out.write(");background-repeat:no-repeat;background-position:top center;background-size:100% auto;height:75px;position:absolute;top:0;left:0;width:100%;z-index:-2;}\n");
      out.write("\t</style>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("</head>\n");
      out.write("\n");
if(UtilMethods.isSet(request.getParameter("popup")) || UtilMethods.isSet(request.getAttribute("popup")) || UtilMethods.isSet(request.getParameter("in_frame"))){ 
      out.write("\n");
      out.write("\t<body class=\"dmundra\" style=\"background:white url()\">\n");
}else{ 
      out.write("\n");
      out.write("\t<body class=\"dmundra\" style=\"visibility:hidden\">\n");
      out.write("\t\t<div class=\"imageBG\"></div>\n");
      out.write("\t\t<div class=\"bannerBG\"></div>\n");
} 
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("        <style>\n");
      out.write("                body{\n");
      out.write("                        background: white;\n");
      out.write("                }\n");
      out.write("        </style>\n");
      out.write("        ");
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

if(request.getSession().getAttribute(ActionErrors.GLOBAL_ERROR) != null){
	request.setAttribute(ActionErrors.GLOBAL_ERROR, request.getSession().getAttribute(ActionErrors.GLOBAL_ERROR));

}


Set<String> messages = new HashSet<String>();
Set<String> errors = new HashSet<String>();

if(request.getAttribute(ActionErrors.GLOBAL_ERROR) !=null){
	ActionErrors aes = (ActionErrors) request.getAttribute(ActionErrors.GLOBAL_ERROR);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		String m = LanguageUtil.get(pageContext, am.getKey());
		if(am.getValues() != null){
			for(int i=0;i<am.getValues().length;i++){
				m = UtilMethods.replace(m, "{" + i + "}", (String) am.getValues()[i]);
			}
		}
		errors.add(m);
	}
}

if(request.getAttribute(Globals.ERROR_KEY) != null){
	ActionErrors aes = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		String m = LanguageUtil.get(pageContext, am.getKey());
		if(am.getValues() != null){
			for(int i=0;i<am.getValues().length;i++){
				m = UtilMethods.replace(m, "{" + i + "}", (String) am.getValues()[i]);
			}
		}
		errors.add(m);
	}
}




if(request.getAttribute(ActionMessages.GLOBAL_MESSAGE) !=null){
	ActionMessages aes = (ActionMessages) request.getAttribute(ActionMessages.GLOBAL_MESSAGE);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		messages.add(am.getKey());
	}
}



if(SessionMessages.contains(session, "message")){
	messages.add((String) SessionMessages.get(session, "message"));
}

if(SessionMessages.contains(session, "error")){
	errors.add((String) SessionMessages.get(session, "error"));
}
if(SessionMessages.contains(session, "custommessage")){
	messages.add((String) SessionMessages.get(session, "custommessage"));
}

if(SessionMessages.contains(request, "message")){
	messages.add((String) SessionMessages.get(request, "message"));
}
if(SessionMessages.contains(request, "error")){
	errors.add((String) SessionMessages.get(request, "error"));
}
if(SessionMessages.contains(request, "custommessage")){
	messages.add((String) SessionMessages.get(request, "custommessage"));
}



SessionMessages.clear(session);
SessionMessages.clear(request);
request.getSession().removeAttribute("org.apache.struts.action.MESSAGE");
request.getSession().removeAttribute("org.apache.struts.action.ERROR");




      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\tdojo.require(\"dojo.fx\");\n");
      out.write("\tdojo.require(\"dijit.layout.ContentPane\");\n");
      out.write("\n");
      out.write("\n");
      out.write("\tvar messagesCount = 0;\n");
      out.write("\tvar messageYIncrement = 60;\n");
      out.write("\tvar occupiedPositions = new Array();\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t");
if(errors.size() > 0){
      out.write("\n");
      out.write("\t\t   dojo.addOnLoad(\n");
      out.write("\t\t\t\t   function () {\n");
      out.write("\t\t   \t\t\t\tshowDotCMSErrorMessage(\"<ul>");
for(String x : errors){
      out.write("<li>");
      out.print(UtilMethods.replace(LanguageUtil.get(pageContext, x), "\"", "\\\"") );
      out.write("</li>");
} 
      out.write("</ul>\")\n");
      out.write("\t\t\t\t   }\n");
      out.write("\t\t   \t\t);\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t\t");
if(messages.size() > 0){
      out.write("\n");
      out.write("\t\t   dojo.addOnLoad(\n");
      out.write("\t\t\t\t   function () {\n");
      out.write("\t\t   \t\t\t\tshowDotCMSSystemMessage(\"<div class=\\\"messageIcon resolveIcon\\\"></div>\" + \"");
for(String x : messages){
      out.write(' ');
      out.print(UtilMethods.replace(LanguageUtil.get(pageContext, x), "\"", "\\\"") );
} 
      out.write("\")\n");
      out.write("\t\t\t\t   }\n");
      out.write("\t\t   \t\t);\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSSystemMessage(message){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(message, false);\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSSystemMessage(message, isError){\n");
      out.write("\n");
      out.write("\t\t\tvar position = 40;\n");
      out.write("\n");
      out.write("\t\t\tif(occupiedPositions.length > 0)\n");
      out.write("\t\t\t\tposition = occupiedPositions[occupiedPositions.length - 1] + messageYIncrement;\t\n");
      out.write("\t\t\t\toccupiedPositions.push(position);\n");
      out.write("\t\t\t\t\n");
      out.write("\n");
      out.write("\t\t\tvar className = isError? 'systemErrorsHolder':'systemMessagesHolder';\n");
      out.write("\t\t\tvar holdingDiv = dojo.create(\"div\", { \t\n");
      out.write("\t\t\t\tid : \"systemMessagesWrapper\" + messagesCount, \n");
      out.write("\t\t\t\tclassName : className,\n");
      out.write("\t\t\t\tstyle: { top: position + '%' }\n");
      out.write("\t\t\t}, dojo.body());\n");
      out.write("\n");
      out.write("\t\t\tvar className = isError? 'errorMessages':'systemMessages';\n");
      out.write("\t\t\tvar systemMessages = dojo.create(\"div\", { \t\n");
      out.write("\t\t\t\tid: \"systemMessages\" + messagesCount,\n");
      out.write("\t\t\t\tclassName: className\n");
      out.write("\t\t\t}, holdingDiv);\n");
      out.write("\n");
      out.write("\t\t\tsystemMessages.innerHTML = message;\n");
      out.write("\t\t\n");
      out.write("\t\t\tdojo.connect(dijit.byId(\"systemMessages\"), \"onClick\", hideDotCMSSystemMessage);\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\tvar hideFn = dojo.partial(hideDotCMSSystemMessage, messagesCount);\n");
      out.write("\t\t\tdojo.connect(holdingDiv, 'onclick', hideFn);\t\n");
      out.write("\t\n");
      out.write("\t\t\tvar hideFn = dojo.partial(hideDotCMSSystemMessage, messagesCount);\n");
      out.write("\t\t\tvar fadeOutFn = dojo.fadeOut({node: \"systemMessages\" + messagesCount, delay: 10, duration: 0, onEnd: hideFn }).play;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar fadeIn = dojo.fadeIn({node: \"systemMessages\" + messagesCount, duration: 2000, onEnd: fadeOutFn });\n");
      out.write("\t\t\tfadeIn.play();\n");
      out.write("\t\n");
      out.write("\t\t\tvar ttl = message.split(\" \").length;\n");
      out.write("\t\t\tttl = ttl * 200;\n");
      out.write("\t\t\tif(ttl < 1000){\n");
      out.write("\t\t\t\tttl = 1000;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\thideMessagesHandler = setTimeout(hideFn,ttl);\n");
      out.write("\t\n");
      out.write("\t\t\tmessagesCount++;\n");
      out.write("\t\t\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tfunction hideDotCMSSystemMessage(messageId){\n");
      out.write("\t\n");
      out.write("\t\t\tvar currentY = parseInt(dojo.byId(\"systemMessagesWrapper\" + messageId).style.top);\n");
      out.write("\t\t\toccupiedPositions = dojo.filter(occupiedPositions, function (x) {\n");
      out.write("\t\t\t\treturn x != currentY;\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t\tdojo.fadeOut({node: \"systemMessagesWrapper\" + messageId}).play();\n");
      out.write("\t\t\tdojo.destroy(\"systemMessagesWrapper\" + messageId);\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tvar hideErrorsHandler;\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSErrorMessage(message){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(message, true);\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  Constants.TEXT_HTML_DIR + tilesContent , out, false);
      out.write("\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\tfunction dotMakeBodVisible(){\n");
      out.write("\t\t\tdojo.style(dojo.body(), \"visibility\", \"visible\");\n");
      out.write("\t\t}\n");
      out.write("\t\t\r\n");
      out.write("\t\tdojo.addOnLoad(dotMakeBodVisible);\n");
      out.write("\t\tsetTimeout( \"dotMakeBodVisible\",2000);\n");
      out.write("\t\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write('\n');
}else{ 
      out.write("\n");
      out.write("\n");
      out.write("        ");
      out.write('\n');
      out.write('\n');
      out.write('\n');


	String dojoPath = Config.getStringProperty("path.to.dojo");
	if(!UtilMethods.isSet(dojoPath)){
		// Change dojopath in dotmarketing-config.properties!
		response.sendError(500, "No dojo path variable (path.to.dojo) set in the property file");
	}
	String agent = request.getHeader("User-Agent");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","01 Jan 2000 00:00:00 GMT");


      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:bi=\"urn:bi\" xmlns:csp=\"urn:csp\">\n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"x-ua-compatible\" content=\"IE=edge\" >\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("\t\n");
      out.write("\t<meta content=\"no-cache\" http-equiv=\"Cache-Control\" />\n");
      out.write("\t<meta content=\"no-cache\" http-equiv=\"Pragma\" />\n");
      out.write("\t<meta content=\"0\" http-equiv=\"Expires\" />\n");
      out.write("\t<meta name=\"Expire\" content=\"Now\" />\n");
      out.write("\t\n");
      out.write("\t<link rel=\"shortcut icon\" href=\"//www.dotcms.com/global/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("\t<title>");
      out.print( DOTCMS_PORTAL_PAGE_TITLE );
      out.write("</title>\n");
      out.write("\t \n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t@import \"/html/common/css.jsp?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"; \n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dijit/themes/dmundra/dmundra.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dijit/themes/dmundra/Grid.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"");
      out.print(dojoPath);
      out.write("/dojox/widget/Calendar/Calendar.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        @import \"/html/js/dotcms/dijit/image/image_tools.css?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("\n");
      out.write("    </style>\n");
      out.write("\t\n");
      out.write("\t<!--[if IE]>\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/html/css/iehacks.css\" />\n");
      out.write("\t<![endif]--> \n");
      out.write("    \n");
      out.write("    ");

    String dojoLocaleConfig = "locale:'en-us'";    
    if(locale != null){
    	dojoLocaleConfig = "locale:'"+locale.getLanguage() + "-" + locale.getCountry().toLowerCase() + "',";    	
    }    
    
      out.write("\n");
      out.write("   \t    \n");
      out.write("   \t<script type=\"text/javascript\">\n");
      out.write("       \tdjConfig={\n");
      out.write("               parseOnLoad: true,\n");
      out.write("               useXDomain: false,\n");
      out.write("               isDebug: false,\n");
      out.write("               ");
      out.print(dojoLocaleConfig);
      out.write("\n");
      out.write("               modulePaths: { dotcms: \"/html/js/dotcms\" }\n");
      out.write("       };\n");
      out.write("\t   \n");
      out.write("\t   \tfunction isInodeSet(x){\n");
      out.write("\t\t\treturn (x && x != undefined && x!=\"\" && x.length>15);\n");
      out.write("\t\t}\n");
      out.write("   \t</script>\n");
      out.write("\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(dojoPath);
      out.write("/dojo/dojo.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/html/common/javascript.jsp?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/engine.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/util.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/TemplateAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/HostAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/ContainerAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/RoleAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/BrowserAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/UserAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/dwr/interface/InodeAjax.js?b=");
      out.print( ReleaseInfo.getVersion() );
      out.write("\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("\t\tdojo.require(\"dijit.Dialog\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Button\");\n");
      out.write("\t\tdojo.require(\"dijit.form.CheckBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.DateTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.FilteringSelect\");\n");
      out.write("\t\tdojo.require(\"dijit.form.TextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.ValidationTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Textarea\");\n");
      out.write("\t\tdojo.require(\"dijit.Menu\");\n");
      out.write("\t\tdojo.require(\"dijit.MenuItem\");\n");
      out.write("\t\tdojo.require(\"dijit.MenuSeparator\");\n");
      out.write("\t\tdojo.require(\"dijit.ProgressBar\");\n");
      out.write("\t\tdojo.require(\"dijit.PopupMenuItem\");\n");
      out.write("\t\tdojo.require('dijit.layout.TabContainer');\n");
      out.write("\t\tdojo.require('dijit.layout.ContentPane');\n");
      out.write("\t\tdojo.require('dojox.layout.ContentPane');\n");
      out.write("\t\tdojo.require(\"dijit.layout.BorderContainer\");\n");
      out.write("\t\tdojo.require(\"dijit.TitlePane\");\n");
      out.write("\t\tdojo.require(\"dijit.Tooltip\");\n");
      out.write("\t\tdojo.require(\"dojo.parser\");\n");
      out.write("\t\tdojo.require(\"dojo.fx\");\n");
      out.write("\t\tdojo.require(\"dotcms.dojo.data.UsersReadStore\");\n");
      out.write("\t\tdojo.require(\"dojox.form.DropDownSelect\");\n");
      out.write("\t\tdojo.require(\"dojox.json.query\");\n");
      out.write("\t\tdojo.require(\"dijit.form.NumberTextBox\");\n");
      out.write("\t\tdojo.require(\"dijit.form.TimeTextBox\");\n");
      out.write("\t\tdojo.require(\"dotcms.dijit.image.ImageEditor\");\n");
      out.write("\t\tdojo.require(\"dojox.widget.Calendar\");\n");
      out.write("\t\tdojo.require(\"dojo.date.locale\");\n");
      out.write("\t\tdojo.require(\"dojox.form.Uploader\");\n");
      out.write("        dojo.require(\"dojox.form.uploader.FileList\");\n");
      out.write("        dojo.require(\"dojox.form.uploader.plugins.HTML5\");\n");
      out.write("        dojo.require(\"dijit.form.ComboBox\");\n");
      out.write("   \t\tdojo.require(\"dijit.TooltipDialog\");\n");
      out.write("\t\tdojo.require(\"dojox.grid.EnhancedGrid\");\n");
      out.write("\t\tdojo.require(\"dojox.grid.enhanced.plugins.Menu\");\n");
      out.write("\t\tdojo.require(\"dojo.data.ItemFileReadStore\");\n");
      out.write("\t\tdojo.require(\"dijit.form.Form\");\n");
      out.write("\t\tdojo.require(\"dojo.io.script\");\n");
      out.write("\t\t\n");
      out.write("\t\tdojo.addOnLoad(function () {\n");
      out.write("\t\t\tdojo.global.DWRUtil = dwr.util;\n");
      out.write("\t\t\tdojo.global.DWREngine = dwr.engine;\n");
      out.write("\t\t\tdwr.engine.setErrorHandler(DWRErrorHandler);\n");
      out.write("\t\t\tdwr.engine.setWarningHandler(DWRErrorHandler);\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\tfunction DWRErrorHandler(msg, e) {\n");
      out.write("\t\t\tconsole.log(msg, e);\n");
      out.write("\t\t}\n");
      out.write("\t\tvar dojoDom=dojo.require(\"dojo.dom\");\n");
      out.write("\t\tvar dojoDomGeometry=dojo.require(\"dojo.dom-geometry\");\n");
      out.write("\t\tvar dojoStyle=dojo.require(\"dojo.dom-style\");\n");
      out.write("\t\tdojo.coords = function(elem,xx) {\n");
      out.write("            var mb=dojoDomGeometry.getMarginBox(elem,dojoStyle.getComputedStyle(elem));\n");
      out.write("            var abs=dojoDomGeometry.position(elem,xx);\n");
      out.write("            mb.x=abs.x;\n");
      out.write("            mb.y=abs.y;\n");
      out.write("            mb.w=abs.w;\n");
      out.write("            mb.h=abs.h;\n");
      out.write("            return mb;\n");
      out.write("        };\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\t");
 String dotBackImage = (!UtilMethods.isSet(company.getHomeURL()) || "localhost".equals(company.getHomeURL())) ? "/html/images/backgrounds/bg-3.jpg" : company.getHomeURL();
      out.write("\n");
      out.write("\t<style>\n");
      out.write("\t\t.imageBG{background-color:");
      out.print( company.getSize() );
      out.write(";background-image:url(");
      out.print( dotBackImage );
      out.write(");background-repeat:no-repeat;background-position:top center;background-size:100% auto;height:75px;position:absolute;top:0;left:0;width:100%;z-index:-2;}\n");
      out.write("\t</style>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("</head>\n");
      out.write("\n");
if(UtilMethods.isSet(request.getParameter("popup")) || UtilMethods.isSet(request.getAttribute("popup")) || UtilMethods.isSet(request.getParameter("in_frame"))){ 
      out.write("\n");
      out.write("\t<body class=\"dmundra\" style=\"background:white url()\">\n");
}else{ 
      out.write("\n");
      out.write("\t<body class=\"dmundra\" style=\"visibility:hidden\">\n");
      out.write("\t\t<div class=\"imageBG\"></div>\n");
      out.write("\t\t<div class=\"bannerBG\"></div>\n");
} 
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("        \n");
      out.write("        <div id=\"doc3\" class=\"yui-t7\">\n");
      out.write("                <div id=\"hd\">\n");
      out.write("                        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"menu\">\n");
      out.write("        <ul class=\"level1 horizontal\" id=\"root\">\n");
      out.write("\n");
      out.write("        ");
for(int l=0;l< layouts.length ;l++){
                String tabName =LanguageUtil.get(pageContext, LanguageUtil.get(pageContext, layouts[l].getName())); 
                String tabDescription = (!UtilMethods.isSet(layouts[l].getDescription())) ? "&nbsp;" :layouts[l].getDescription() ;
                if(!tabDescription.equals("&nbsp;")){
                         tabDescription = LanguageUtil.get(pageContext,tabDescription) ;
                 }
                
                
                List<String> portletIDs = layouts[l].getPortletIds();
                boolean isSelectedTab = (layout != null && layouts !=null && layout.getId().equals(layouts[l].getId()));
                PortletURLImpl portletURLImpl = new PortletURLImpl(request, portletIDs.get(0), layouts[l].getId(), false);
                String tabHREF = portletURLImpl.toString() + "&dm_rlout=1&r=" + System.currentTimeMillis();
      out.write("\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                        <li class=\"level1 ");
      out.print((isSelectedTab) ? "Active" : "");
      out.write("\">\n");
      out.write("                                <a href=\"");
      out.print(tabHREF );
      out.write("\">\n");
      out.write("                                        <div class=\"tabLeft\">\n");
      out.write("                                                <div class=\"navMenu-title\">");
      out.print(tabName );
      out.write("</div>\n");
      out.write("                                                <div class=\"navMenu-subtitle\">");
      out.print(tabDescription );
      out.write("</div>\n");
      out.write("                                                <div class=\"navMenu-arrow\">&nbsp;</div>\n");
      out.write("                                        </div>\n");
      out.write("                                </a>\n");
      out.write("                                ");
if( portletIDs.size()>1){
      out.write("\n");
      out.write("                                        <span class=\"tabRight\"></span>\n");
      out.write("                                        <ul class=\"level2 dropdown\">\n");
      out.write("                                                ");
for(int i=0;i< portletIDs.size() ;i++){
                                                        
                                                        portletURLImpl = new PortletURLImpl(request, portletIDs.get(i), layouts[l].getId(), false);                        
                                                        String linkHREF = portletURLImpl.toString() + "&dm_rlout=1&r=" + System.currentTimeMillis();
                                                        String linkName = LanguageUtil.get(pageContext,"javax.portlet.title." + portletIDs.get(i)); 
                                                        
                                                        
                                                        if("EXT_LICENSE_MANAGER".equals(portletIDs.get(i))){
                                                                request.setAttribute("licenseManagerPortletUrl", linkHREF);
                                                        }
                                                        
                                                        
      out.write("\n");
      out.write("                                                        \n");
      out.write("                                                        \n");
      out.write("                                                        \n");
      out.write("                                                        <li class=\"level2 dotCMS_");
      out.print(portletIDs.get(i));
      out.write("\"><a href=\"");
      out.print(linkHREF );
      out.write("\"><span></span>");
      out.print(linkName );
      out.write("</a></li>\n");
      out.write("                                                ");
} 
      out.write("\n");
      out.write("                                        </ul>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                        </li>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("        </ul>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\n");
      out.write("\n");
      out.write("        var _myWindowWidth=0;\n");
      out.write("        \n");
      out.write("        function smallifyMenu(){\n");
      out.write("                \n");
      out.write("                // move our menu out of sight for rendering....\n");
      out.write("                var m = dojo.byId(\"menu\");\n");
      out.write("        \n");
      out.write("                var viewport = dijit.getViewport();\n");
      out.write("                var  screenWidth= (viewport.w -40);\n");
      out.write("                //alert(screenWidth);\n");
      out.write("                if(_myWindowWidth == screenWidth){\n");
      out.write("                        return;\n");
      out.write("                }\n");
      out.write("                _myWindowWidth = screenWidth;\n");
      out.write("                // tabW keeps track of the tab width\n");
      out.write("                var tabW = 0;\n");
      out.write("                var fattestTab =0;\n");
      out.write("                var tabs = dojo.query(\"li.level1\");\n");
      out.write("                for(i = 0;i<tabs.length;i++){\n");
      out.write("                        var x = tabs[i];\n");
      out.write("                        var classes = dojo.attr(x, \"class\");\n");
      out.write("                        classes = classes.replace(\" smallify\", \"\");\n");
      out.write("                        dojo.attr(x, \"class\", classes);\n");
      out.write("                        width =  (dojo.coords(x)).w;\n");
      out.write("                        if(width > fattestTab){\n");
      out.write("                                fattestTab=width;\n");
      out.write("                        }\n");
      out.write("                        tabW = tabW + (dojo.coords(x)).w;\n");
      out.write("                }\n");
      out.write("                screenWidth = screenWidth - fattestTab;\n");
      out.write("                //alert(fattestTab);\n");
      out.write("                // get the top of our menu (to see if we are wrapping)\n");
      out.write("                var firstTop = (dojo.coords(tabs[0])).t;\n");
      out.write("                var lastTop = (dojo.coords(tabs[(tabs.length-1)])).t;\n");
      out.write("                if(tabW > screenWidth || lastTop > firstTop){\n");
      out.write("                        for(i = tabs.length;i>0;i--){\n");
      out.write("                                lastTop = (dojo.coords(tabs[(tabs.length-1)])).t;\n");
      out.write("                                var x = tabs[i-1];\n");
      out.write("        \n");
      out.write("                                var width = (dojo.coords(x)).w;\n");
      out.write("                                tabW = tabW-width;\n");
      out.write("                                var classes = dojo.attr(x, \"class\");\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("                                \n");
      out.write("                                if(tabW > screenWidth || lastTop > firstTop){\n");
      out.write("                                        classes = classes + \" smallify\";\n");
      out.write("                                        dojo.attr(x, \"class\", classes);\n");
      out.write("                                        width = (dojo.coords(x)).w;\n");
      out.write("                                        tabW = tabW+width;\n");
      out.write("                                }else{\n");
      out.write("                                        break;\n");
      out.write("                                }\n");
      out.write("                        }\n");
      out.write("                }\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        }\n");
      out.write("        dojo.addOnLoad (smallifyMenu);\n");
      out.write("        dojo.connect(window, \"onresize\", this, \"smallifyMenu\");\n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("</script>");
      out.write("\n");
      out.write("                        ");
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

    boolean isCommunity = ("100".equals(System
            .getProperty("dotcms_level")));
    String licenseMessage = null;
    String licenseURL = "http://www.dotcms.com/buy-now";
    List<Layout> layoutListForLicenseManager=null;
    try {
        if (isCommunity) {
        	licenseMessage = LanguageUtil.get(pageContext, "Try-Enterprise-Now") + "!" ;

            layoutListForLicenseManager=APILocator.getLayoutAPI().findAllLayouts();
            for (Layout layoutForLicenseManager:layoutListForLicenseManager) {
                List<String> portletIdsForLicenseManager=layoutForLicenseManager.getPortletIds();
                if (portletIdsForLicenseManager.contains("EXT_LICENSE_MANAGER")) {
                    licenseURL = "/c/portal/layout?p_l_id=" + layoutForLicenseManager.getId() +"&p_p_id=EXT_LICENSE_MANAGER&p_p_action=0";
                    break;
                }

            }



        } else {
            boolean isPerpetual = new Boolean(System
                    .getProperty("dotcms_license_perpetual"));
            boolean isTrial = true;
            try{
                isTrial = (System.getProperty("dotcms_license_client_name").toLowerCase().indexOf("trial") >-1);
            }
            catch(Exception e){}
            Date td = new Date();
            Date ed = new Date(Long.parseLong(System
                    .getProperty("dotcms_valid_until")));

            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(td);
            start.set(Calendar.HOUR, 0);
            start.set(Calendar.MINUTE, 0);
            start.set(Calendar.SECOND, 0);
            start.set(Calendar.MILLISECOND, 0);
            end.setTime(ed);
            long milliseconds1 = start.getTimeInMillis();
            long milliseconds2 = end.getTimeInMillis();
            long diff = milliseconds2 - milliseconds1;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (!isPerpetual && isTrial) {
                if (diffDays > 1) {
                	licenseMessage = LanguageUtil.format(pageContext, "days-remaining-Purchase-now",diffDays,false);
                    licenseURL = "http://dotcms.com/buy-now";
                }
                if (diffDays == 1) {
                	licenseMessage = LanguageUtil.format(pageContext, "day-remaining-Purchase-now",diffDays,false);
                    licenseURL = "http://dotcms.com/buy-now";
                }
                if (diffDays < 1) {
                	licenseMessage = LanguageUtil.get(pageContext,"Trial-Expired-Purchase-Now");
                    licenseURL = "http://dotcms.com/buy-now";
                }

            }else if (!isPerpetual) {
                if (diffDays > 1 && diffDays < 30) {
                	licenseMessage = LanguageUtil.format(pageContext, "Subscription-expires-in-days",diffDays,false);
                    licenseURL = "http://dotcms.com/renew-now";
                }
                if (diffDays == 1) {
                	licenseMessage = LanguageUtil.format(pageContext, "Subscription-expires-in-day",diffDays,false);
                    licenseURL = "http://dotcms.com/renew-now";
                }
                if (diffDays < 1) {
                	licenseMessage = LanguageUtil.get(pageContext,"Subscription-Expired-Renew-Now");
                    licenseURL = "http://dotcms.com/buy-now";
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    boolean hasRolesPortlet = false;
    boolean hasLicenseManagerPortlet = false;
    String portletLinkHREF = "";
    java.util.List<com.dotmarketing.business.Layout> userLayouts = APILocator.getLayoutAPI().loadLayoutsForUser(user);
    if ((userLayouts != null) && (userLayouts.size() != 0)) {
        int count = 0;
        for (int i = 0; i < userLayouts.size(); i++) {
            java.util.List<String> portletids = userLayouts.get(i).getPortletIds();
            for (int j = 0; j < portletids.size(); j++) {
                if (portletids.get(j).equals("EXT_ROLE_ADMIN") && !hasRolesPortlet) {
                    hasRolesPortlet = true;

                }

                if (portletids.get(j).equals("EXT_LICENSE_MANAGER") && !hasLicenseManagerPortlet) {
                    hasLicenseManagerPortlet = true;
                }
            }
            if(!userLayouts.get(i).getName().equals("CMS Admin")){
                count+=1;
            }
        }

    }
    if (!hasRolesPortlet || hasLicenseManagerPortlet) {

         java.util.List<com.dotmarketing.business.Layout> allLayouts = APILocator.getLayoutAPI().findAllLayouts();
         com.liferay.portal.model.Portlet portlet = null;
         String ticket = "";
        if(!hasRolesPortlet){
          String roleAdminPortletId = "";
          portlet = APILocator.getPortletAPI().findPortlet("EXT_ROLE_ADMIN");
            if(portlet!=null){
                roleAdminPortletId = portlet.getPortletId();
            }

        if ((allLayouts != null) && (allLayouts.size() != 0)) {
            for (int i = 0; i < allLayouts.size(); i++) {
                if(allLayouts.get(i).getName().equals("CMS Admin")){
                    PortletURLImpl portletURLImpl = new PortletURLImpl(
                            request, roleAdminPortletId, allLayouts.get(i).getId(), false);
                     ticket  = String.valueOf(portletURLImpl.hashCode());
                    if(request.getSession().getAttribute("roleAdminOverrideTicket")==null){
                        request.getSession().setAttribute("roleAdminOverrideTicket",ticket);
                    }else{
                        ticket = (String)request.getSession().getAttribute("roleAdminOverrideTicket");
                    }

                    portletLinkHREF = portletURLImpl.toString()+ "&dm_rlout=1&roleAdminOverrideTicket="+ticket;
                    break;
                }
            }
         }
        }

        if(!hasLicenseManagerPortlet){
            String licenseManagerPortletId = "";
            portlet = APILocator.getPortletAPI().findPortlet("EXT_LICENSE_MANAGER");
                if(portlet!=null){
                    licenseManagerPortletId = portlet.getPortletId();
                }


            if ((allLayouts != null) && (allLayouts.size() != 0)) {

                if (APILocator.getUserAPI().isCMSAdmin(user)){
                        PortletURLImpl portletURLImpl = new PortletURLImpl(
                                request, licenseManagerPortletId, allLayouts.get(1).getId(), false);
                        ticket  = String.valueOf(portletURLImpl.hashCode());
                        if(request.getSession().getAttribute("licenseManagerOverrideTicket")==null){
                            request.getSession().setAttribute("licenseManagerOverrideTicket",ticket);
                        }else{
                            ticket = (String)request.getSession().getAttribute("licenseManagerOverrideTicket");
                        }

                        licenseURL = portletURLImpl.toString()+ "&dm_rlout=1&licenseManagerOverrideTicket="+ticket;

                    }
                }

            }
    }else {
        if(request.getSession().getAttribute("roleAdminOverrideTicket")!=null){
           request.getSession().removeAttribute("roleAdminOverrideTicket");
        }

        if(request.getSession().getAttribute("licenseManagerOverrideTicket")!=null){
               request.getSession().removeAttribute("licenseManagerOverrideTicket");
        }
    }





        boolean emailAuth = false;
        if(company.getAuthType().equals(com.liferay.portal.model.Company.AUTH_TYPE_EA)) {
            emailAuth = true;
        }


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
      out.write("<script type=\"text/javascript\" src=\"/dwr/interface/UserAjax.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("dojo.require(\"dojo.cookie\");\n");
      out.write("\n");
      out.write("    dojo.addOnLoad (function () {\n");
      out.write("        dojo.connect(dijit.byId('portal_login_as_user'), 'onChange',\n");
      out.write("            function (val) {\n");
      out.write("                if(val != '') {\n");
      out.write("                    ");
try {
      out.write("\n");
      out.write("                        UserAjax.hasUserRoles(dijit.byId('portal_login_as_user').value.split(\"-\")[1], [ '");
      out.print(APILocator.getRoleAPI().loadRoleByKey(
                        Role.ADMINISTRATOR).getId());
      out.write("', '");
      out.print(APILocator.getRoleAPI().loadCMSAdminRole().getId());
      out.write("' ], portal_loginAs_checkAdminRole);\n");
      out.write("                    ");
} catch (Exception ex) {

                      }
      out.write("\n");
      out.write("                } else {\n");
      out.write("                    //dojo.byId('portal_loginasbutton').disabled = true;\n");
      out.write("                }\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        dojo.connect(dijit.byId('portal_loginasbutton'), 'onClick', function () {\n");
      out.write("            dojo.byId('portal_login_as_users_form').submit();\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        ");
if (request.getSession().getAttribute("portal_login_as_error") != null) {
      out.write("\n");
      out.write("                dojo.byId('portal_loginas_errors').innerHTML = '");
      //  bean:message
      org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f0 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
      _jspx_th_bean_005fmessage_005f0.setPageContext(_jspx_page_context);
      _jspx_th_bean_005fmessage_005f0.setParent(null);
      // /html/common/nav_sub_inc.jsp(239,64) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_bean_005fmessage_005f0.setKey((String) request.getSession().getAttribute(
                            "portal_login_as_error"));
      int _jspx_eval_bean_005fmessage_005f0 = _jspx_th_bean_005fmessage_005f0.doStartTag();
      if (_jspx_th_bean_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
      out.write("';\n");
      out.write("                portal_showLoginAs();\n");
      out.write("\n");
      out.write("        ");
request.getSession().removeAttribute(
                            "portal_login_as_error");
                }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    });\n");
      out.write("\n");
      out.write("    function clearErrorMsg()\n");
      out.write("    {\n");
      out.write("    \t ");
request.getSession().removeAttribute("portal_login_as_error");
      out.write("\n");
      out.write("         dojo.byId('portal_loginas_errors').innerHTML = '';\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function portal_loginAs_checkAdminRole(isAdmin) {\n");
      out.write("        var wrapper = dojo.byId('portal_login_as_password_wrapper');\n");
      out.write("        if(isAdmin) {\n");
      out.write("            dojo.style(wrapper, { display: '' });\n");
      out.write("        } else {\n");
      out.write("            dojo.style(wrapper, { display: 'none' });\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function portal_showLoginAs() {\n");
      out.write("        dijit.byId('portal_login_as_users_wrapper').show();\n");
      out.write("        dijit.byId('portal_login_as_user').value = '';\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function portal_cancelLoginAs() {\n");
      out.write("        dijit.byId('portal_login_as_users_wrapper').hide();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function portal_showRolesPortlet() {\n");
      out.write("        dijit.byId('portal_roles_wrapper').show();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function portal_showMyAccount() {\n");
      out.write("        dijit.byId('portal_myaccount_wrapper').show();\n");
      out.write("        var userId='");
      out.print( user.getUserId());
      out.write("';\n");
      out.write("        if(userId!='null'){\n");
      out.write("            editUserMyAccount(userId);\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    ");
 if(emailAuth) { 
      out.write("\n");
      out.write("    \tvar emailAuth = true;\n");
      out.write("    ");
 } else { 
      out.write("\n");
      out.write("    \tvar emailAuth = false;\n");
      out.write("    ");
 }  
      out.write("\n");
      out.write("\n");
      out.write("    var passwordsDontMatchError = '");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "passwords-dont-match-error")) );
      out.write("';\n");
      out.write("    var userSavedMsg = '");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "User-Info-Saved")) );
      out.write("';\n");
      out.write("    var sameEmailAlreadyRegisteredErrorMsg = '");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "user-email-already-registered")) );
      out.write("';\n");
      out.write("    var sameUserIdAlreadyRegisteredErrorMsg = '");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "user-id-already-registered")) );
      out.write("';\n");
      out.write("    var doNotHavePermissionsMsg = '");
      out.print( UtilMethods.escapeSingleQuotes(LanguageUtil.get(pageContext, "dont-have-permissions-msg")) );
      out.write("';\n");
      out.write("\n");
      out.write("\n");
      out.write("    var currentUserMyAccount;\n");
      out.write("    function editUserMyAccount(userIdMyAccount) {\n");
      out.write("        var user = {\n");
      out.write("        \t\tid : \"");
      out.print( user.getUserId() );
      out.write("\",\n");
      out.write("        \t\ttype : \"user\",\n");
      out.write("                firstName : \"");
      out.print( user.getFirstName() );
      out.write("\",\n");
      out.write("                lastName : \"");
      out.print( user.getLastName() );
      out.write("\",\n");
      out.write("                emailaddress : \"");
      out.print(user.getEmailAddress());
      out.write("\",\n");
      out.write("                name : \"");
      out.print(user.getFullName());
      out.write("\"\n");
      out.write("        };\n");
      out.write("\n");
      out.write("        //Global user variable\n");
      out.write("        currentUserMyAccount = user;\n");
      out.write("\n");
      out.write("        //SEtting user info form\n");
      out.write("        if(!emailAuth) {\n");
      out.write("            dijit.byId('userIdMyAccount').attr('value', user.id);\n");
      out.write("            dijit.byId('userIdMyAccount').setDisabled(true);\n");
      out.write("        } else {\n");
      out.write("            dojo.byId('userIdValueMyAccount').innerHTML = user.id;\n");
      out.write("            dojo.byId('userIdMyAccount').value = user.id;\n");
      out.write("        }\n");
      out.write("        dojo.byId('userIdLabelMyAccount').style.display = '';\n");
      out.write("        dojo.byId('userIdValueMyAccount').style.display = '';\n");
      out.write("        dijit.byId('firstNameMyAccount').attr('value', user.firstName);\n");
      out.write("        dijit.byId('lastNameMyAccount').attr('value', user.lastName);\n");
      out.write("        dijit.byId('emailAddressMyAccount').attr('value', user.emailaddress);\n");
      out.write("        dijit.byId('passwordMyAccount').attr('value', '********');\n");
      out.write("        dijit.byId('passwordCheckMyAccount').attr('value', '********');\n");
      out.write("\n");
      out.write("        dojo.query(\".fullUserName\").forEach(function (elem) { elem.innerHTML = '<b>' + user.name + '</b>'; });\n");
      out.write("\n");
      out.write("        userChangedMyAccount = false;\n");
      out.write("        //dojo.byId('loadingUserProfile').style.display = 'none';\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("    //Handler from when the user info has changed\n");
      out.write("    var userChangedMyAccount = false;\n");
      out.write("    function userInfoChangedMyAccount() {\n");
      out.write("        userChangedMyAccount = true;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    //Handler from when the user password has changed\n");
      out.write("    var passwordChangedMyAccount = false;\n");
      out.write("    function userPasswordChangedMyAccount() {\n");
      out.write("        userChangedMyAccount = true;\n");
      out.write("        passwordChangedMyAccount = true;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    //Handler to save the user details\n");
      out.write("    function saveUserDetailsMyAccount() {\n");
      out.write("\n");
      out.write("        //If user has not changed do nothing\n");
      out.write("        if(!userChangedMyAccount) {\n");
      out.write("            alert(userSavedMsg);\n");
      out.write("            return;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        //If the form is not valid focus on the first not valid field and\n");
      out.write("        //hightlight the other not valid ones\n");
      out.write("        if(!dijit.byId('userInfoFormMyAccount').validate()) {\n");
      out.write("            return;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        var myAccountpassswordValue;\n");
      out.write("        var myAccountreenterPasswordValue;\n");
      out.write("        if(passwordChangedMyAccount) {\n");
      out.write("            myAccountpassswordValue = dijit.byId('passwordMyAccount').attr('value');\n");
      out.write("            myAccountreenterPasswordValue = dijit.byId('passwordCheckMyAccount').attr('value');\n");
      out.write("            if(myAccountpassswordValue != myAccountreenterPasswordValue) {\n");
      out.write("                alert(passwordsDontMatchError);\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        //Executing the update user logic\n");
      out.write("        var callbackOptions = {\n");
      out.write("            callback: saveUserCallbackMyAccount,\n");
      out.write("            exceptionHandler: saveUserExceptionMyAccount\n");
      out.write("        };\n");
      out.write("        UserAjax.updateUser(currentUserMyAccount.id, currentUserMyAccount.id, dijit.byId('firstNameMyAccount').attr('value'),\n");
      out.write("                dijit.byId('lastNameMyAccount').attr('value'),\n");
      out.write("                dijit.byId('emailAddressMyAccount').attr('value'), myAccountpassswordValue, callbackOptions);\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    //Callback from the server to confirm the user saved\n");
      out.write("    function saveUserCallbackMyAccount (userId) {\n");
      out.write("        if(userId) {\n");
      out.write("            userChangedMyAccount = false;\n");
      out.write("            passwordChangedMyAccount = false;\n");
      out.write("            alert(userSavedMsg);\n");
      out.write("            editUserMyAccount(userId);\n");
      out.write("        } else {\n");
      out.write("            alert(userSaveFailedMsg);\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function saveUserExceptionMyAccount(message, exception){\n");
      out.write("        if(exception.javaClassName == 'com.dotmarketing.business.DuplicateUserException') {\n");
      out.write("            if(emailAuth) {\n");
      out.write("                alert(sameEmailAlreadyRegisteredErrorMsg);\n");
      out.write("            }\n");
      out.write("            else {\n");
      out.write("                alert(sameUserIdAlreadyRegisteredErrorMsg);\n");
      out.write("            }\n");
      out.write("        } else if(exception.javaClassName == 'com.dotmarketing.exception.DotSecurityException'){\n");
      out.write("                alert(doNotHavePermissionsMsg);\n");
      out.write("            }else {\n");
      out.write("            alert(\"Server error: \" + exception);\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function showAutoUpdaterPopUp(){\n");
      out.write("        dijit.byId('portal_autoupdater_wrapper').show();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function remindMeLater(){\n");
      out.write("        dojo.cookie(\"autoupdater-reminder\", \"true\",{expires: 7, path: '/'});\n");
      out.write("        dijit.byId('portal_autoupdater_wrapper').hide();\n");
      out.write("        document.getElementById('autoUpdaterLink').style.display = \"none\";\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    var major;\n");
      out.write("    var minor;\n");
      out.write("\n");
      out.write("    function whatsNew(version){\n");
      out.write("        var href = \"http://dotcms.com/dotCMSVersions#\";\n");
      out.write("        if (version=='current'){\n");
      out.write("            href+=\"");
      out.print( ReleaseInfo.getVersion() );
      out.write("\";\n");
      out.write("        }else if(version=='major'){\n");
      out.write("            href+=major;\n");
      out.write("        }else if(version=='minor'){\n");
      out.write("            href+=minor;\n");
      out.write("        }\n");
      out.write("        window.open(href);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function howTo(){\n");
      out.write("\n");
      out.write("        var href = \"");
      out.print( LanguageUtil.get(pageContext, "Autoupdater-link"));
      out.write("\";\n");
      out.write("        window.open(href);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function setTextContent(element, text) {\n");
      out.write("        while (element.firstChild!==null)\n");
      out.write("            element.removeChild(element.firstChild); // remove all existing content\n");
      out.write("        element.appendChild(document.createTextNode(text));\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function enableAutoUpdaterLink(versionInfo){\n");
      out.write("        major = versionInfo.major;\n");
      out.write("        minor = versionInfo.minor;\n");
      out.write("        build = versionInfo.buildNumber;\n");
      out.write("        if(major!=''){\n");
      out.write("            document.getElementById('majorUpdate').style.display=\"\";\n");
      out.write("            setTextContent(document.getElementById('wnMajor'), major + \"(");
      out.print( LanguageUtil.get(pageContext, "Whats-new") );
      out.write(")\");\n");
      out.write("        }\n");
      out.write("        if(minor!=''){\n");
      out.write("            document.getElementById('minorUpdate').style.display=\"\";\n");
      out.write("            if(build!='' && build!='0'){\n");
      out.write("                setTextContent(document.getElementById('wnMinor'),  minor + \" Build: \" + build +\"(");
      out.print( LanguageUtil.get(pageContext, "Whats-new") );
      out.write(")\");\n");
      out.write("            }else{\n");
      out.write("                setTextContent(document.getElementById('wnMinor'),minor + \"(");
      out.print( LanguageUtil.get(pageContext, "Whats-new") );
      out.write(")\");\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("        var autoUpdaterCookie = dojo.cookie(\"autoupdater-reminder\");\n");
      out.write("        if(!autoUpdaterCookie || autoUpdaterCookie=='false' || autoUpdaterCookie=='undefined'){\n");
      out.write("            if(versionInfo.showUpdate){\n");
      out.write("                 //var animArgs = {node: 'autoUpdaterLink',duration: 1000, delay: 50};\n");
      out.write("                 //dojo.fadeIn(animArgs).play();\n");
      out.write("                 dojo.style(\"autoUpdaterLink\", \"display\", \"inline-block\");\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\tfunction showAboutDotCMSMessage(){\n");
      out.write("       var myDialog = dijit.byId(\"dotBackEndDialog\");\n");
      out.write("       myDialog.titleNode.innerHTML=\"");
      out.print( LanguageUtil.get(pageContext, "about") );
      out.write(" dotCMS\";\n");
      out.write("       dijit.byId(\"dotBackEndDialogCP\").setHref(\"/html/portal/about.jsp\");\n");
      out.write("       myDialog.show();\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction showDisclaimerMessage(){\n");
      out.write("       var myDialog = dijit.byId(\"dotBackEndDialog\");\n");
      out.write("       myDialog.titleNode.innerHTML=\"");
      out.print( UnicodeLanguageUtil.get(pageContext, "disclaimer") );
      out.write("\";\n");
      out.write("       dijit.byId(\"dotBackEndDialogCP\").setHref(\"/html/portal/disclaimer.jsp\");\n");
      out.write("       myDialog.show();\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t function toggleAccount() {\n");
      out.write("\t\tif(document.getElementById(\"account-menu\").style.display==\"none\") {\n");
      out.write("\t\t\tdocument.getElementById(\"account-menu\").style.display=\"\";\n");
      out.write("\t\t\tdocument.getElementById(\"closeTab\").style.display=\"\";\n");
      out.write("\t\t\tdocument.getElementById(\"account-trigger\").setAttribute(\"class\", \"trigger-on\");\n");
      out.write("\t\t} else {\n");
      out.write("\t\t\tdocument.getElementById(\"account-menu\").style.display=\"none\";\n");
      out.write("\t\t\tdocument.getElementById(\"closeTab\").style.display=\"none\";\n");
      out.write("\t\t\tdocument.getElementById(\"account-trigger\").setAttribute(\"class\", \"trigger-off\");\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<div id=\"admin-banner-logo-div\">\n");
      out.write("    <h1>dotCMS</h1>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- Start Site Tools -->\n");
 if (signedIn) { 
      out.write("\n");
      out.write("    <div id=\"admin-site-tools-div\">\n");
      out.write("\t\t<!-- Updates -->\n");
      out.write("        ");
 if (licenseMessage != null) { 
      out.write("\n");
      out.write("            <a class=\"goEnterpriseLink\" href=\"");
      out.print(licenseURL);
      out.write("\"><span class=\"keyIcon\"></span>");
      out.print(licenseMessage);
      out.write("</a>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("   \t\t<a id=\"autoUpdaterLink\" style=\"display:none;\" class=\"goEnterpriseLink\"  href=\"javascript: showAutoUpdaterPopUp();\"><span class=\"exclamation-red\"></span>");
      out.print( LanguageUtil.get(pageContext, "Update-available") );
      out.write("</a>\n");
      out.write("\n");
      out.write("\t\t<!-- User Actions -->\n");
      out.write("\t\t");
 if (request.getSession().getAttribute(WebKeys.PRINCIPAL_USER_ID) == null) { 
      out.write("\n");
      out.write("\t\t\t<a href=\"#\" id=\"account-trigger\" onclick=\"toggleAccount();\" class=\"trigger-off\">");
      out.print(user.getFullName());
      out.write("</a>\n");
      out.write("\t    ");
 } else { 
      out.write("\n");
      out.write("\t        <a href=\"");
      out.print(CTX_PATH);
      out.write("/portal");
      out.print(PortalUtil.getAuthorizedPath(request));
      out.write("/logout_as?referer=");
      out.print(CTX_PATH);
      out.write("\"><span class=\"plusIcon\"></span>");
      if (_jspx_meth_bean_005fmessage_005f1(_jspx_page_context))
        return;
      out.write(' ');
      out.print(user.getFullName());
      out.write("</a>\n");
      out.write("\t    ");
 } 
      out.write("\n");
      out.write("\t</div>\n");
 } 
      out.write("\n");
      out.write("\n");
      out.write("<!-- End Site Tools -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- User Info Drop Down -->\n");
      out.write("\n");
      out.write("<div id=\"account-menu\" class=\"account-flyout\" style=\"display:none;\">\n");
      out.write("\t<div class=\"my-account\">\n");
      out.write("\t\t<h3>");
      out.print(user.getFullName());
      out.write("</h3>\n");
      out.write("\t\t<a href=\"javascript: portal_showMyAccount();toggleAccount();\">\n");
      out.write("\t\t\t");
      out.print(LanguageUtil.get(pageContext, "my-account"));
      out.write("\n");
      out.write("\t\t</a>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"service-links\">\n");
      out.write("\t\t<a  href=\"javascript:showAboutDotCMSMessage();toggleAccount();\" >");
      out.print( LanguageUtil.get(pageContext, "about")  );
      out.write("</a>\n");
      out.write("\t\t<a  href=\"javascript:showDisclaimerMessage();toggleAccount();\">");
      out.print( LanguageUtil.get(pageContext, "disclaimer")  );
      out.write("</a>\n");
      out.write("\t\t<a  href=\"#\" onClick=\"dijit.byId('showSupport').show();toggleAccount();\">");
      out.print(LanguageUtil.get(pageContext, "Support") );
      out.write("</a>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"login-out\">\n");
      out.write("\t\t<table>\n");
      out.write("\t\t\t<tbody>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t<a  href=\"");
      out.print(CTX_PATH);
      out.write("/portal");
      out.print(PortalUtil.getAuthorizedPath(request));
      out.write("/logout?referer=");
      out.print(CTX_PATH);
      out.write('"');
      out.write('>');
      out.print(LanguageUtil.get(pageContext, "Logout"));
      out.write("</a>\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /html/common/nav_sub_inc.jsp(554,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest( APILocator.getRoleAPI().doesUserHaveRole(user, APILocator.getRoleAPI().loadRoleByKey(Role.LOGIN_AS)) && request.getSession().getAttribute(WebKeys.PRINCIPAL_USER_ID) == null );
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t<td style=\"border-left:1px solid #d0d0d0;width:50%;\"><a href=\"javascript: portal_showLoginAs();toggleAccount();\">");
          if (_jspx_meth_bean_005fmessage_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context))
            return;
          out.write("</a></td>\n");
          out.write("\t\t\t\t    ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      out.write("\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</tbody>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("    ");
 if (!hasRolesPortlet && APILocator.getUserAPI().isCMSAdmin(user) ) { 
      out.write("\n");
      out.write("        <a class=\"rolePortletLink\" href=\"");
      out.print(portletLinkHREF);
      out.write('"');
      out.write('>');
      if (_jspx_meth_bean_005fmessage_005f3(_jspx_page_context))
        return;
      out.write("</a>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"closeTab\" onClick=\"toggleAccount();\" style=\"display:none;\"></div>\n");
      out.write("\n");
      out.write("<!-- End User Info Drop Down -->\n");
      out.write("\n");
      out.write("<!-- Start Login As pop up -->\n");
      out.write("    ");

        if (APILocator.getRoleAPI().doesUserHaveRole(user,
            APILocator.getRoleAPI().loadRoleByKey(Role.LOGIN_AS))
            && request.getSession().getAttribute(
                WebKeys.PRINCIPAL_USER_ID) == null) {
    
      out.write("\n");
      out.write("        <div id=\"portal_login_as_users_wrapper\" dojoType=\"dijit.Dialog\"  style=\"display:none;height:180px;vertical-align: middle;padding-top:15px\\9;\" draggable=\"false\" >\n");
      out.write("            <div id=\"portal_loginas_errors\"></div>\n");
      out.write("            <form id=\"portal_login_as_users_form\" action=\"");
      out.print(CTX_PATH);
      out.write("/portal");
      out.print(PortalUtil.getAuthorizedPath(request));
      out.write("/login_as?referer=");
      out.print(CTX_PATH);
      out.write("\" method=\"post\">\n");
      out.write("                <div id=\"portal_login_as_users_select\" class=\"formRow\" style=\"text-align:center;\">\n");
      out.write("                    <div dojoType=\"dotcms.dojo.data.UsersReadStore\" jsId=\"usersStore\" includeRoles=\"false\"></div>\n");
      out.write("                    ");
      if (_jspx_meth_bean_005fmessage_005f4(_jspx_page_context))
        return;
      out.write(" : &nbsp;\n");
      out.write("                        <select id=\"portal_login_as_user\" name=\"portal_login_as_user\" dojoType=\"dijit.form.FilteringSelect\" onchange=\"clearErrorMsg()\"\n");
      out.write("                        store=\"usersStore\" searchDelay=\"300\" pageSize=\"30\" labelAttr=\"name\"\n");
      out.write("                        invalidMessage=\"");
      out.print(LanguageUtil.get(pageContext,
                            "Invalid-option-selected"));
      out.write("\"\n");
      out.write("                        ></select>\n");
      out.write("                </div><br/>\n");
      out.write("                <div class=\"formRow\" id=\"portal_login_as_password_wrapper\" style=\"text-align:center; display: none;\">\n");
      out.write("                    ");
      if (_jspx_meth_bean_005fmessage_005f5(_jspx_page_context))
        return;
      out.write(" <input type=\"password\" name=\"portal_login_as_password\" autocomplete=\"off\" id=\"portal_login_as_password\"/><br/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"formRow\"  style=\"text-align:center\">\n");
      out.write("                    <button dojoType=\"dijit.form.Button\" id=\"portal_loginasbutton\" iconClass=\"loginAsIcon\">");
      if (_jspx_meth_bean_005fmessage_005f6(_jspx_page_context))
        return;
      out.write("</button>\n");
      out.write("                    <button dojoType=\"dijit.form.Button\" iconClass=\"cancelIcon\" onclick=\"portal_cancelLoginAs()\">");
      if (_jspx_meth_bean_005fmessage_005f7(_jspx_page_context))
        return;
      out.write("</button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("<!-- End Login As pop up -->\n");
      out.write("\n");
      out.write("<!-- START User Detail pop up -->\n");
      out.write("    <div id=\"portal_myaccount_wrapper\" dojoType=\"dijit.Dialog\" style=\"display:none;height:400px;width:450px;vertical-align: middle;\" draggable=\"false\" >\n");
      out.write("        <div style=\"overflow-y:auto;\" dojoType=\"dijit.layout.ContentPane\">\n");
      out.write("            <div class=\"yui-g nameHeader\">\n");
      out.write("                <div class=\"yui-u first\">\n");
      out.write("                    <span id=\"fullUserNameMyAccount\" class=\"fullUserName\"></span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div style=\"padding:0 0 10px 0; border-bottom:1px solid #ccc;\">\n");
      out.write("                <form id=\"userInfoFormMyAccount\" dojoType=\"dijit.form.Form\">\n");
      out.write("                    <input type=\"hidden\" name=\"userPasswordChanged\" value=\"false\"/>\n");
      out.write("                    <dl>\n");
      out.write("                        ");
 if(emailAuth) { 
      out.write("\n");
      out.write("                            <dt id=\"userIdLabelMyAccount\">");
      out.print( LanguageUtil.get(pageContext, "User-ID") );
      out.write(": <input type=\"hidden\" id=\"userIdMyAccount\" name=\"userId\" value=\"\"/></dt>\n");
      out.write("                            <dd id=\"userIdValueMyAccount\"></dd>\n");
      out.write("                        ");
 } else {
      out.write("\n");
      out.write("                            <dt id=\"userIdLabelMyAccount\">");
      out.print( LanguageUtil.get(pageContext, "User-ID") );
      out.write(":</dt>\n");
      out.write("                            <dd id=\"userIdValueMyAccount\"><input id=\"userIdMyAccount\" type=\"text\" onkeyup=\"userInfoChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" disabled=\"disabled\" /></dd>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                        <dt>");
      out.print( LanguageUtil.get(pageContext, "First-Name") );
      out.write(":</dt>\n");
      out.write("                        <dd><input id=\"firstNameMyAccount\" type=\"text\" onkeyup=\"userInfoChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" /></dd>\n");
      out.write("                        <dt>");
      out.print( LanguageUtil.get(pageContext, "Last-Name") );
      out.write(":</dt>\n");
      out.write("                        <dd><input id=\"lastNameMyAccount\" type=\"text\" onkeyup=\"userInfoChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" /></dd>\n");
      out.write("                        <dt>");
      out.print( LanguageUtil.get(pageContext, "Email-Address") );
      out.write(":</dt>\n");
      out.write("                        <dd><input id=\"emailAddressMyAccount\" type=\"text\" onkeyup=\"userInfoChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" /></dd>\n");
      out.write("                        <dt>");
      out.print( LanguageUtil.get(pageContext, "Password") );
      out.write(":</dt>\n");
      out.write("                        <dd><input id=\"passwordMyAccount\" type=\"password\" autocomplete=\"off\" onkeyup=\"userPasswordChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" /></dd>\n");
      out.write("                        <dt>");
      out.print( LanguageUtil.get(pageContext, "Password-Again") );
      out.write(":</dt>\n");
      out.write("                        <dd><input id=\"passwordCheckMyAccount\" type=\"password\" autocomplete=\"off\" onkeyup=\"userPasswordChangedMyAccount()\" required=\"true\" invalidMessage=\"Required.\" dojoType=\"dijit.form.ValidationTextBox\" /></dd>\n");
      out.write("                    </dl>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"clear\"></div>\n");
      out.write("            <div class=\"buttonRow\">\n");
      out.write("                <button dojoType=\"dijit.form.Button\" onclick=\"saveUserDetailsMyAccount()\" type=\"button\" iconClass=\"saveIcon\">");
      out.print( LanguageUtil.get(pageContext, "Save") );
      out.write("</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("<!-- END User Detail pop up -->\n");
      out.write("\n");
 if (APILocator.getUserAPI().isCMSAdmin(user)) { 
      out.write("\n");
      out.write("\n");
      out.write("    <!-- START Auto Updater pop up -->\n");
      out.write("        <div id=\"portal_autoupdater_wrapper\" dojoType=\"dijit.Dialog\" style=\"display:none;height:280px;width:450px;vertical-align: middle;\" draggable=\"false\" >\n");
      out.write("            <div style=\"overflow-y:auto;\" dojoType=\"dijit.layout.ContentPane\">\n");
      out.write("                <div class=\"yui-g nameHeader\">\n");
      out.write("                    <div class=\"yui-u first\">\n");
      out.write("                        <span class=\"fullUserName\"><b>");
      out.print( LanguageUtil.get(pageContext, "Update-available") );
      out.write("</b></span>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div style=\"padding:0 0 10px 0; border-bottom:1px solid #ccc;\" align=\"center\">\n");
      out.write("                <b>");
      out.print( LanguageUtil.get(pageContext, "You-are-running") );
      out.write(":</b> <a href=\"javascript:whatsNew('current');\">");
      out.print( ReleaseInfo.getVersion() );
      out.write('(');
      out.print( LanguageUtil.get(pageContext, "Whats-new") );
      out.write(")</a>\n");
      out.write("                <br />\n");
      out.write("                <br />\n");
      out.write("                <div id=\"minorUpdate\" style=\"display:none;\">\n");
      out.write("                <b>");
      out.print( LanguageUtil.get(pageContext, "Latest-update") );
      out.write(":</b> <a id=\"wnMinor\" href=\"javascript:whatsNew('minor');\"></a>\n");
      out.write("                </div>\n");
      out.write("                <br />\n");
      out.write("                <div id=\"majorUpdate\" style=\"display:none;\">\n");
      out.write("                <b>");
      out.print( LanguageUtil.get(pageContext, "New-version") );
      out.write(":</b> <a id=\"wnMajor\" href=\"javascript:whatsNew('major');\"></a>\n");
      out.write("                </div>\n");
      out.write("                <br />\n");
      out.write("                <br />\n");
      out.write("                <a href=\"javascript:howTo();\">");
      out.print( LanguageUtil.get(pageContext, "Learn-how-to-use-the-autoupdater") );
      out.write("</a>\n");
      out.write("                <br />\n");
      out.write("                <br />\n");
      out.write("                </div>\n");
      out.write("                <div class=\"clear\"></div>\n");
      out.write("                <div class=\"buttonRow\">\n");
      out.write("                    <button dojoType=\"dijit.form.Button\" onclick=\"remindMeLater()\" type=\"button\">");
      out.print( LanguageUtil.get(pageContext, "Remind-me-later") );
      out.write("</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    <!-- END Auto Updater pop up -->\n");
      out.write("\n");
      out.write("\t<!-- About pop up -->\n");
      out.write("\t<div id=\"dotBackEndDialog\" dojoType=\"dijit.Dialog\" style=\"display:none\" title=\"");
      out.print( LanguageUtil.get(pageContext, "about") );
      out.write(" dotCMS\">\n");
      out.write("\t\t<!-- Server Info -->\n");
      out.write("\t\t\t");
String serverId = Config.getStringProperty("DIST_INDEXATION_SERVER_ID");
      out.write("\n");
      out.write("\t\t\t");
 if (UtilMethods.isSet(serverId)){ 
      out.write("\n");
      out.write("\t\t\t\t<div class=\"serverID\"><strong>Server:</strong> ");
      out.print(serverId);
      out.write("</div>\n");
      out.write("\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t<!-- End Server Info -->\n");
      out.write("\t\t<div dojoType=\"dijit.layout.ContentPane\" style=\"width:400px;height:150px;\" class=\"box\" hasShadow=\"true\" id=\"dotBackEndDialogCP\"></div>\n");
      out.write("\t\t<div class=\"copyright\">&copy;");
      out.print(new GregorianCalendar().get(Calendar.YEAR));
      out.write(" dotCMS Software, LLC ");
      out.print( LanguageUtil.get(pageContext, "All-rights-reserved") );
      out.write(".</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<!-- Support pop up -->\n");
      out.write("\t<div id=\"showSupport\" dojoType=\"dijit.Dialog\" style=\"display: none\">\n");
      out.write("\t\t<table width=\"600\"><tr>\n");
      out.write("\t\t\t<td valign=\"top\" width=\"50%\" style=\"padding:10px;border-right:1px solid #dcdcdc;\">\n");
      out.write("\t\t\t\t<h2>");
      out.print(LanguageUtil.get(pageContext, "Report-a-Bug") );
      out.write("</h2>\n");
      out.write("\t\t\t\t<p>");
      out.print(LanguageUtil.get(pageContext, "dotCMS-is-dedicated-to-quality-assurance") );
      out.write("</p>\n");
      out.write("\t\t\t\t<div class=\"buttonRow\">\n");
      out.write("\t\t\t\t\t<button dojoType=\"dijit.form.Button\" iconClass=\"bugIcon\" onclick=\"window.open('https://github.com/dotCMS');\">\n");
      out.write("\t\t\t\t\t\t");
      out.print(LanguageUtil.get(pageContext, "Report-a-Bug") );
      out.write("\n");
      out.write("\t\t\t\t\t</button>\n");
      out.write("                </div>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t\t<td valign=\"top\" width=\"50%\" style=\"padding:10px 10px 10px 20px;\">\n");
      out.write("\t\t\t\t<h2>");
      out.print(LanguageUtil.get(pageContext, "Professional-Support") );
      out.write("</h2>\n");
      out.write("\t\t\t\t<p>");
      out.print(LanguageUtil.get(pageContext, "Let-our-support-engineers-get-you-back-on-track") );
      out.write("</p>\n");
      out.write("\t\t\t\t<div style=\"text-align:center;font-size:146.5%;color:#990000;\">+1 877-9-DOTCMS</div>\n");
      out.write("\t\t\t\t<div style=\"text-align:center;font-size:77%;color:#999;\">");
      out.print(LanguageUtil.get(pageContext, "Toll-Free") );
      out.write("+1 877-936-8267</div>\n");
      out.write("\t\t\t\t<div style=\"text-align:center;font-size:146.5%;color:#999;\">");
      out.print(LanguageUtil.get(pageContext, "or") );
      out.write("</div>\n");
      out.write("\t\t\t\t<div style=\"text-align:center;\">\n");
      out.write("\t\t\t\t\t<a href=\"http://www.dotcms.org/enterprise/\" target=\"_blank\">");
      out.print(LanguageUtil.get(pageContext, "Click-here-to-login-to-your-account") );
      out.write("</a>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr></table>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    ");
if(APILocator.getUserAPI().isCMSAdmin(user)) {
	    if(session.getAttribute("_autoupdater_showUpdate") == null) {
      out.write("\n");
      out.write("\t        <script type=\"text/javascript\" src=\"/dwr/interface/AutoUpdaterAjax.js\"></script>\n");
      out.write("\t        <script>\n");
      out.write("\t            dojo.addOnLoad(function(){\n");
      out.write("\t                AutoUpdaterAjax.getLatestVersionInfo(dojo.hitch(enableAutoUpdaterLink));\n");
      out.write("\t            })\n");
      out.write("\t        </script>\n");
      out.write("\t    ");
}else if((Boolean) session.getAttribute("_autoupdater_showUpdate") == true) {
      out.write("\n");
      out.write("\t        <script>\n");
      out.write("\t            dojo.addOnLoad(function(){\n");
      out.write("\t                var enableupdatevar = {\n");
      out.write("\t                        showUpdate : ");
      out.print(session.getAttribute("_autoupdater_showUpdate"));
      out.write(",\n");
      out.write("\t                        major : \"");
      out.print(session.getAttribute("_autoupdater_major"));
      out.write("\",\n");
      out.write("\t                        minor : \"");
      out.print(session.getAttribute("_autoupdater_minor"));
      out.write("\",\n");
      out.write("\t                        buildNumber : '0'\n");
      out.write("\t                }\n");
      out.write("\t                if(enableupdatevar.showUpdate){\n");
      out.write("\t                    enableAutoUpdaterLink(enableupdatevar);\n");
      out.write("\t                }\n");
      out.write("\t            })\n");
      out.write("\t        </script>\n");
      out.write("    ");
	}
    }
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("                        ");
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

if(request.getSession().getAttribute(ActionErrors.GLOBAL_ERROR) != null){
	request.setAttribute(ActionErrors.GLOBAL_ERROR, request.getSession().getAttribute(ActionErrors.GLOBAL_ERROR));

}


Set<String> messages = new HashSet<String>();
Set<String> errors = new HashSet<String>();

if(request.getAttribute(ActionErrors.GLOBAL_ERROR) !=null){
	ActionErrors aes = (ActionErrors) request.getAttribute(ActionErrors.GLOBAL_ERROR);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		String m = LanguageUtil.get(pageContext, am.getKey());
		if(am.getValues() != null){
			for(int i=0;i<am.getValues().length;i++){
				m = UtilMethods.replace(m, "{" + i + "}", (String) am.getValues()[i]);
			}
		}
		errors.add(m);
	}
}

if(request.getAttribute(Globals.ERROR_KEY) != null){
	ActionErrors aes = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		String m = LanguageUtil.get(pageContext, am.getKey());
		if(am.getValues() != null){
			for(int i=0;i<am.getValues().length;i++){
				m = UtilMethods.replace(m, "{" + i + "}", (String) am.getValues()[i]);
			}
		}
		errors.add(m);
	}
}




if(request.getAttribute(ActionMessages.GLOBAL_MESSAGE) !=null){
	ActionMessages aes = (ActionMessages) request.getAttribute(ActionMessages.GLOBAL_MESSAGE);
	Iterator it = aes.get();
	while(it.hasNext()){
		ActionMessage am = (ActionMessage) it.next();
		messages.add(am.getKey());
	}
}



if(SessionMessages.contains(session, "message")){
	messages.add((String) SessionMessages.get(session, "message"));
}

if(SessionMessages.contains(session, "error")){
	errors.add((String) SessionMessages.get(session, "error"));
}
if(SessionMessages.contains(session, "custommessage")){
	messages.add((String) SessionMessages.get(session, "custommessage"));
}

if(SessionMessages.contains(request, "message")){
	messages.add((String) SessionMessages.get(request, "message"));
}
if(SessionMessages.contains(request, "error")){
	errors.add((String) SessionMessages.get(request, "error"));
}
if(SessionMessages.contains(request, "custommessage")){
	messages.add((String) SessionMessages.get(request, "custommessage"));
}



SessionMessages.clear(session);
SessionMessages.clear(request);
request.getSession().removeAttribute("org.apache.struts.action.MESSAGE");
request.getSession().removeAttribute("org.apache.struts.action.ERROR");




      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\tdojo.require(\"dojo.fx\");\n");
      out.write("\tdojo.require(\"dijit.layout.ContentPane\");\n");
      out.write("\n");
      out.write("\n");
      out.write("\tvar messagesCount = 0;\n");
      out.write("\tvar messageYIncrement = 60;\n");
      out.write("\tvar occupiedPositions = new Array();\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t");
if(errors.size() > 0){
      out.write("\n");
      out.write("\t\t   dojo.addOnLoad(\n");
      out.write("\t\t\t\t   function () {\n");
      out.write("\t\t   \t\t\t\tshowDotCMSErrorMessage(\"<ul>");
for(String x : errors){
      out.write("<li>");
      out.print(UtilMethods.replace(LanguageUtil.get(pageContext, x), "\"", "\\\"") );
      out.write("</li>");
} 
      out.write("</ul>\")\n");
      out.write("\t\t\t\t   }\n");
      out.write("\t\t   \t\t);\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t\t");
if(messages.size() > 0){
      out.write("\n");
      out.write("\t\t   dojo.addOnLoad(\n");
      out.write("\t\t\t\t   function () {\n");
      out.write("\t\t   \t\t\t\tshowDotCMSSystemMessage(\"<div class=\\\"messageIcon resolveIcon\\\"></div>\" + \"");
for(String x : messages){
      out.write(' ');
      out.print(UtilMethods.replace(LanguageUtil.get(pageContext, x), "\"", "\\\"") );
} 
      out.write("\")\n");
      out.write("\t\t\t\t   }\n");
      out.write("\t\t   \t\t);\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSSystemMessage(message){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(message, false);\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSSystemMessage(message, isError){\n");
      out.write("\n");
      out.write("\t\t\tvar position = 40;\n");
      out.write("\n");
      out.write("\t\t\tif(occupiedPositions.length > 0)\n");
      out.write("\t\t\t\tposition = occupiedPositions[occupiedPositions.length - 1] + messageYIncrement;\t\n");
      out.write("\t\t\t\toccupiedPositions.push(position);\n");
      out.write("\t\t\t\t\n");
      out.write("\n");
      out.write("\t\t\tvar className = isError? 'systemErrorsHolder':'systemMessagesHolder';\n");
      out.write("\t\t\tvar holdingDiv = dojo.create(\"div\", { \t\n");
      out.write("\t\t\t\tid : \"systemMessagesWrapper\" + messagesCount, \n");
      out.write("\t\t\t\tclassName : className,\n");
      out.write("\t\t\t\tstyle: { top: position + '%' }\n");
      out.write("\t\t\t}, dojo.body());\n");
      out.write("\n");
      out.write("\t\t\tvar className = isError? 'errorMessages':'systemMessages';\n");
      out.write("\t\t\tvar systemMessages = dojo.create(\"div\", { \t\n");
      out.write("\t\t\t\tid: \"systemMessages\" + messagesCount,\n");
      out.write("\t\t\t\tclassName: className\n");
      out.write("\t\t\t}, holdingDiv);\n");
      out.write("\n");
      out.write("\t\t\tsystemMessages.innerHTML = message;\n");
      out.write("\t\t\n");
      out.write("\t\t\tdojo.connect(dijit.byId(\"systemMessages\"), \"onClick\", hideDotCMSSystemMessage);\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\tvar hideFn = dojo.partial(hideDotCMSSystemMessage, messagesCount);\n");
      out.write("\t\t\tdojo.connect(holdingDiv, 'onclick', hideFn);\t\n");
      out.write("\t\n");
      out.write("\t\t\tvar hideFn = dojo.partial(hideDotCMSSystemMessage, messagesCount);\n");
      out.write("\t\t\tvar fadeOutFn = dojo.fadeOut({node: \"systemMessages\" + messagesCount, delay: 10, duration: 0, onEnd: hideFn }).play;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar fadeIn = dojo.fadeIn({node: \"systemMessages\" + messagesCount, duration: 2000, onEnd: fadeOutFn });\n");
      out.write("\t\t\tfadeIn.play();\n");
      out.write("\t\n");
      out.write("\t\t\tvar ttl = message.split(\" \").length;\n");
      out.write("\t\t\tttl = ttl * 200;\n");
      out.write("\t\t\tif(ttl < 1000){\n");
      out.write("\t\t\t\tttl = 1000;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\thideMessagesHandler = setTimeout(hideFn,ttl);\n");
      out.write("\t\n");
      out.write("\t\t\tmessagesCount++;\n");
      out.write("\t\t\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tfunction hideDotCMSSystemMessage(messageId){\n");
      out.write("\t\n");
      out.write("\t\t\tvar currentY = parseInt(dojo.byId(\"systemMessagesWrapper\" + messageId).style.top);\n");
      out.write("\t\t\toccupiedPositions = dojo.filter(occupiedPositions, function (x) {\n");
      out.write("\t\t\t\treturn x != currentY;\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t\t\tdojo.fadeOut({node: \"systemMessagesWrapper\" + messageId}).play();\n");
      out.write("\t\t\tdojo.destroy(\"systemMessagesWrapper\" + messageId);\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\t}\n");
      out.write("\t\n");
      out.write("\t\tvar hideErrorsHandler;\n");
      out.write("\t\n");
      out.write("\t\tfunction showDotCMSErrorMessage(message){\n");
      out.write("\t\t\tshowDotCMSSystemMessage(message, true);\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div id=\"bd\">\n");
      out.write("                   ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  Constants.TEXT_HTML_DIR + tilesContent , out, false);
      out.write("\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div>\n");
      out.write("                        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
if((DbConnectionFactory.isOracle() ||  DbConnectionFactory.isMsSql()) 
		&& "100".equals(System.getProperty("dotcms_level")) 
		&& session.getAttribute("db-community-edition-warning") ==null){ 
      out.write('\n');
      out.write('	');
      out.write('	');
session.setAttribute("db-community-edition-warning", "1");  
      out.write("\n");
      out.write("\t<script>\n");
      out.write("\t\tfunction closeCotDbWarningDialog(){\n");
      out.write("\t\t\tdijit.byId('dotDbWarningDialog').hide();\n");
      out.write("\t\t\t");
if(request.getAttribute("licenseManagerPortletUrl") != null){ 
      out.write("\n");
      out.write("\t\t\t\twindow.location='");
      out.print(request.getAttribute("licenseManagerPortletUrl") );
      out.write("';\n");
      out.write("\t\t\t");
}
      out.write("\n");
      out.write("\t\t}\n");
      out.write("\t</script>\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t<div id=\"dotDbWarningDialog\" dojoType=\"dijit.Dialog\" style=\"display:none\" title=\"");
      out.print( LanguageUtil.get(pageContext, "db-community-edition-warning-title") );
      out.write("\">\n");
      out.write("\t\t<div dojoType=\"dijit.layout.ContentPane\" style=\"width:400px;height:150px;\" class=\"box\" hasShadow=\"true\" id=\"dotDbWarningDialogCP\">\n");
      out.write("\t\t\t");
      out.print( LanguageUtil.get(pageContext, "db-community-edition-warning-text") );
      out.write("\n");
      out.write("\t\t\t<br>&nbsp;<br>\n");
      out.write("\t\t\t<div class=\"buttonRow\">\n");
      out.write("\t\t\t\t<button dojoType=\"dijit.form.Button\" onClick=\"closeCotDbWarningDialog()\" iconClass=\"cancelIcon\">");
      out.print( LanguageUtil.get(pageContext, "close") );
      out.write("</button>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<script>\n");
      out.write("\t\tdojo.addOnLoad (function(){\n");
      out.write("\t\t\tdojo.style(dijit.byId(\"dotDbWarningDialog\").closeButtonNode, \"visibility\", \"hidden\"); \n");
      out.write("\t\t\tdijit.byId(\"dotDbWarningDialog\").show();\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("\t\n");
} 
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("<iframe name=\"hidden_iframe\" id=\"hidden_iframe\" style=\"position:absolute;top:-100px;width:0px; height:0px; border: 0px;\"></iframe>\r\n");
      out.write("<script>\n");
      out.write("\tfunction setKeepAlive(){\n");
      out.write("\t\tvar myId=document.getElementById(\"hidden_iframe\");\n");
      out.write("\t\tmyId.src =\"/html/common/keep_alive.jsp?r=");
      out.print(System.currentTimeMillis());
      out.write("\";\n");
      out.write("\t}\n");
      out.write("\t// 15 minutes\n");
      out.write("\tsetTimeout(\"setKeepAlive()\", 60000 * 15);\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t");
      out.write("\n");
      out.write("                </div>\n");
      out.write("        </div>\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\tfunction dotMakeBodVisible(){\n");
      out.write("\t\t\tdojo.style(dojo.body(), \"visibility\", \"visible\");\n");
      out.write("\t\t}\n");
      out.write("\t\t\r\n");
      out.write("\t\tdojo.addOnLoad(dotMakeBodVisible);\n");
      out.write("\t\tsetTimeout( \"dotMakeBodVisible\",2000);\n");
      out.write("\t\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write('\n');
} 
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

  private boolean _jspx_meth_bean_005fmessage_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f1 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f1.setParent(null);
    // /html/common/nav_sub_inc.jsp(525,144) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f1.setKey("logout-as");
    int _jspx_eval_bean_005fmessage_005f1 = _jspx_th_bean_005fmessage_005f1.doStartTag();
    if (_jspx_th_bean_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f2 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /html/common/nav_sub_inc.jsp(555,119) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f2.setKey("login-as");
    int _jspx_eval_bean_005fmessage_005f2 = _jspx_th_bean_005fmessage_005f2.doStartTag();
    if (_jspx_th_bean_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f3 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f3.setParent(null);
    // /html/common/nav_sub_inc.jsp(563,63) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f3.setKey("warning-roles-portlet");
    int _jspx_eval_bean_005fmessage_005f3 = _jspx_th_bean_005fmessage_005f3.doStartTag();
    if (_jspx_th_bean_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f4 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f4.setParent(null);
    // /html/common/nav_sub_inc.jsp(583,20) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f4.setKey("Select-User");
    int _jspx_eval_bean_005fmessage_005f4 = _jspx_th_bean_005fmessage_005f4.doStartTag();
    if (_jspx_th_bean_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f4);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f5 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f5.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f5.setParent(null);
    // /html/common/nav_sub_inc.jsp(591,20) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f5.setKey("enter-your-password");
    int _jspx_eval_bean_005fmessage_005f5 = _jspx_th_bean_005fmessage_005f5.doStartTag();
    if (_jspx_th_bean_005fmessage_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f5);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f6 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f6.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f6.setParent(null);
    // /html/common/nav_sub_inc.jsp(594,107) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f6.setKey("login-as");
    int _jspx_eval_bean_005fmessage_005f6 = _jspx_th_bean_005fmessage_005f6.doStartTag();
    if (_jspx_th_bean_005fmessage_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f6);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f7 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f7.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f7.setParent(null);
    // /html/common/nav_sub_inc.jsp(595,113) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_bean_005fmessage_005f7.setKey("cancel");
    int _jspx_eval_bean_005fmessage_005f7 = _jspx_th_bean_005fmessage_005f7.doStartTag();
    if (_jspx_th_bean_005fmessage_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f7);
    return false;
  }
}
