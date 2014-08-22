package org.apache.jsp.html.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.util.FileUtil;
import com.dotmarketing.util.Constants;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import com.dotmarketing.util.Logger;
import java.io.BufferedInputStream;
import java.util.LinkedHashSet;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import com.dotmarketing.util.Config;
import java.util.Iterator;
import java.util.Set;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.cms.factories.PublicCompanyFactory;
import com.dotmarketing.business.APILocator;
import com.liferay.portal.language.LanguageUtil;
import com.liferay.portal.language.UnicodeLanguageUtil;
import com.liferay.util.ParamUtil;
import com.liferay.util.cal.CalendarUtil;
import com.liferay.portal.util.WebKeys;
import java.util.Locale;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.NoSuchUserException;

public final class javascript_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/html/common/top_js_inc.jsp");
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
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/javascript;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

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


	String dojoPath = Config.getStringProperty("path.to.dojo");
	if(!UtilMethods.isSet(dojoPath)){
		// Change dojopath in dotmarketing-config.properties!
		response.sendError(500, "No dojo path variable (path.to.dojo) set in the property file");
	}
	Set<String> files = new LinkedHashSet<String>();
	Set<String> requires = new LinkedHashSet<String>();

	/**
	* this will concat all the files added below into 1 big .js file
	* which will help both in inital loading and
	* in caching, and in subsequent isUpdated 304 type requests for the files.
	**/
	
	/** generated js **/
	requires.add("/dwr/util.js");
	requires.add("/dwr/interface/TemplateAjax.js");
	requires.add("/dwr/interface/HostAjax.js");
	requires.add("/dwr/interface/ContainerAjax.js");
	requires.add("/dwr/interface/RoleAjax.js");
	requires.add("/dwr/interface/BrowserAjax.js");
	requires.add("/dwr/interface/UserAjax.js");
	requires.add("/dwr/interface/InodeAjax.js");
	requires.add("/dwr/interface/BrowserAjax.js");
	requires.add("/dwr/interface/UserAjax.js");
	requires.add("/dwr/interface/HostAjax.js");
	
	/** static js **/
	files.add(dojoPath + "/dojo/dot-dojo.js");
	files.add("/html/js/dotcms/dojo/data/UsersReadStore.js");
	files.add("/html/js/calendar/calendar_stripped.js");
	files.add("/html/js/calendar/calendar-setup_stripped.js");
	files.add("/html/js/scriptaculous/prototype.js");
	files.add("/html/js/sniffer.js");
	files.add("/html/js/menu.js");
	files.add("/html/js/rollovers.js");
	files.add("/html/js/init.js");
	files.add("/html/js/util.js");
	files.add("/html/js/validation.js");
	files.add("/html/js/dotcms-utils/dotcms-utils.js");
	files.add("/html/js/cms_ui_utils.js");
	//files.add("/html/js/popup.js");
	files.add("/html/js/form_validation.js");
	files.add("/html/js/tag.js");
	files.add("/html/js/uuidUtils.js");
	files.add("/html/js/states_and_countries.js");
	
	
	StringBuilder buff = new StringBuilder();
	for(String x : files){
		buff.append(x);
	}
	Calendar _lastModified = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
	_lastModified.set(Calendar.DAY_OF_YEAR, 0);
	_lastModified.set(Calendar.MINUTE, 0);
	_lastModified.set(Calendar.SECOND, 0);
	_lastModified.set(Calendar.MILLISECOND, 0);
	_lastModified.set(Calendar.HOUR, 0);
	_lastModified.set(Calendar.YEAR, 2010);
	_lastModified.add(Calendar.DAY_OF_YEAR, -(buff.length() + files.size()));

	Date _lastModifiedDate = _lastModified.getTime();
	
	String eTag = "eTag" + buff.toString().hashCode();

	SimpleDateFormat httpDate = new SimpleDateFormat(Constants.RFC2822_FORMAT, Locale.US);
	httpDate.setTimeZone(TimeZone.getTimeZone("GMT"));
	int _daysCache = 365;
	GregorianCalendar expiration = new GregorianCalendar();
	expiration.add(java.util.Calendar.DAY_OF_MONTH, _daysCache);
	int seconds = (_daysCache * 24 * 60 * 60);

	response.setHeader("Expires", httpDate.format(expiration.getTime()));
	response.setHeader("Cache-Control", "public, max-age=" + seconds);
    response.setHeader("ETag", eTag);
    response.setHeader("Last-Modified", httpDate.format(_lastModifiedDate));

    String ifModifiedSince = request.getHeader("If-Modified-Since");
	String ifNoneMatch = request.getHeader("If-None-Match");

	
    if(ifNoneMatch != null){
        if(eTag.equals(ifNoneMatch) || ifNoneMatch.equals("*")){
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED );
            return;
        }
    }
    /* Using the If-Modified-Since Header */
    if(ifModifiedSince != null){
	    try{
	    	
	        Date ifModifiedSinceDate = httpDate.parse(ifModifiedSince);
	        
	        if(_lastModifiedDate.getTime() <= ifModifiedSinceDate.getTime()){

	            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED );
	            return;
	        }
	    }
	    catch(Exception e){}
	}

	for(String x : files){
		File f = new File(FileUtil.getRealPath(x));
		if (f.exists()) {
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			while (dis.available() != 0) {

				out.println(dis.readLine());

			}

			fis.close();
			bis.close();
			dis.close();

		} else {
			Logger.fatal(this.getClass(), "Cannot find " + f.getAbsolutePath());
			response.sendError(500, "id10t ERROR " + f.getAbsolutePath() + " not found");
			return;
		}

	}



	
	

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

	HttpSession sess = request.getSession(false);
	Locale locale = null;
	User user = null;
	try {
		user = PortalUtil.getUser(request);
	} catch (NoSuchUserException nsue) {
	}
	

	if(sess != null){
	 	locale = (Locale) sess.getAttribute(org.apache.struts.Globals.LOCALE_KEY);
	}
	if (locale == null && user != null) {
		// Locale should never be null except when the TCK tests invalidate the session
		locale = user.getLocale();
	}
	if(locale ==null){
		locale = PublicCompanyFactory.getDefaultCompany().getLocale();
		
	}

      out.write("\n");
      out.write("\n");
      out.write("\tvar n1Portlets = new Array();\n");
      out.write("\tvar n2Portlets = new Array();\n");
      out.write("\tvar wPortlets = new Array();\n");
      out.write("\n");
      out.write("\tvar CTX_PATH = '");
      out.print( application.getAttribute(WebKeys.CTX_PATH));
      out.write("';\n");
      out.write("\n");
      out.write("\t");

	boolean inFrame = ParamUtil.get(request, "in_frame", false);
	inFrame = (request.getAttribute("in_frame") != null) ? true : inFrame;
	
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\tfunction submitFormAlert() {\n");
      out.write("\t\talert(\"");
      out.print( UnicodeLanguageUtil.get(pageContext, "this-form-has-already-been-submitted") );
      out.write("\");\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t");

	String[] calendarDays = CalendarUtil.getDays(locale, "EEEE");
	
      out.write("\n");
      out.write("\n");
      out.write("\tCalendar._DN = new Array(\n");
      out.write("\t\t\"");
      out.print( calendarDays[0] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[1] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[2] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[3] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[4] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[5] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[6] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[0] );
      out.write("\"\n");
      out.write("\t);\n");
      out.write("\n");
      out.write("\t");

	calendarDays = CalendarUtil.getDays(locale, "EEE");
	
      out.write("\n");
      out.write("\n");
      out.write("\tCalendar._SDN = new Array(\n");
      out.write("\t\t\"");
      out.print( calendarDays[0] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[1] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[2] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[3] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[4] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[5] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[6] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarDays[0] );
      out.write("\"\n");
      out.write("\t);\n");
      out.write("\n");
      out.write("\t");

	String[] calendarMonths = CalendarUtil.getMonths(locale);
	
      out.write("\n");
      out.write("\n");
      out.write("\tCalendar._MN = new Array(\n");
      out.write("\t\t\"");
      out.print( calendarMonths[0] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[1] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[2] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[3] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[4] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[5] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[6] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[7] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[8] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[9] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[10] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[11] );
      out.write("\"\n");
      out.write("\t);\n");
      out.write("\n");
      out.write("\t");

	calendarMonths = CalendarUtil.getMonths(locale, "MMM");
	
      out.write("\n");
      out.write("\n");
      out.write("\tCalendar._SMN = new Array(\n");
      out.write("\t\t\"");
      out.print( calendarMonths[0] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[1] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[2] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[3] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[4] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[5] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[6] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[7] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[8] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[9] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[10] );
      out.write("\",\n");
      out.write("\t\t\"");
      out.print( calendarMonths[11] );
      out.write("\"\n");
      out.write("\t);\n");
      out.write("\n");
      out.write("\tCalendar._TT = {};\n");
      out.write("\n");
      out.write("\tCalendar._TT[\"ABOUT\"] = \"");
      out.print( LanguageUtil.get(pageContext, "date-selection") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"ABOUT\"] = Calendar._TT[\"ABOUT\"].replace(\"{0}\", String.fromCharCode(0x2039));\n");
      out.write("\tCalendar._TT[\"ABOUT\"] = Calendar._TT[\"ABOUT\"].replace(\"{1}\", String.fromCharCode(0x203a));\n");
      out.write("\n");
      out.write("\tCalendar._TT[\"ABOUT_TIME\"] = \"\";\n");
      out.write("\tCalendar._TT[\"CLOSE\"] = \"");
      out.print( LanguageUtil.get(pageContext, "close") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"DAY_FIRST\"] = \"Display %s First\";\n");
      out.write("\tCalendar._TT[\"DRAG_TO_MOVE\"] = \"\";\n");
      out.write("\tCalendar._TT[\"GO_TODAY\"] = \"");
      out.print( LanguageUtil.get(pageContext, "today") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"INFO\"] = \"");
      out.print( LanguageUtil.get(pageContext, "help") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"NEXT_MONTH\"] = \"");
      out.print( LanguageUtil.get(pageContext, "next-month") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"NEXT_YEAR\"] = \"");
      out.print( LanguageUtil.get(pageContext, "next-year") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"PART_TODAY\"] = \"\";\n");
      out.write("\tCalendar._TT[\"PREV_MONTH\"] = \"");
      out.print( LanguageUtil.get(pageContext, "previous-month") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"PREV_YEAR\"] = \"");
      out.print( LanguageUtil.get(pageContext, "previous-year") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"SEL_DATE\"] = \"");
      out.print( LanguageUtil.get(pageContext, "select-date") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"SUN_FIRST\"] = \"\";\n");
      out.write("\tCalendar._TT[\"TIME_PART\"] = \"\";\n");
      out.write("\tCalendar._TT[\"TODAY\"] = \"");
      out.print( LanguageUtil.get(pageContext, "today") );
      out.write("\";\n");
      out.write("\tCalendar._TT[\"WK\"] = \"\";\n");
      out.write("\n");
      out.write("\tCalendar._TT[\"DEF_DATE_FORMAT\"] = \"%Y-%m-%d\";\n");
      out.write("\tCalendar._TT[\"TT_DATE_FORMAT\"] = \"%a, %b %e\";\n");
      out.write("\n");
      out.write("\tCalendar._TT[\"WEEKEND\"] = \"0,6\";\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
      out.write("\t       var myDialog = dijit.byId(\"dotBackEndDialog\");\n");
      out.write("\t       myDialog.titleNode.innerHTML=\"");
      out.print( UnicodeLanguageUtil.get(pageContext, "disclaimer") );
      out.write("\";\n");
      out.write("\t       dijit.byId(\"dotBackEndDialogCP\").setHref(\"/html/portal/disclaimer.jsp\");\n");
      out.write("\t       myDialog.show();\n");
      out.write("\t\t}\n");
      out.write("\t");
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
