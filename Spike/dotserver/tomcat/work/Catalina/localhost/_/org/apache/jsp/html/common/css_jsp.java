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

public final class css_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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


	response.setContentType("text/css");
	String dojoPath = Config.getStringProperty("path.to.dojo");
	if(!UtilMethods.isSet(dojoPath)){
		// Change dojopath in dotmarketing-config.properties!
		response.sendError(500, "No dojo path variable (path.to.dojo) set in the property file");
	}
	Set<String> files = new LinkedHashSet<String>();

	/**
	* this will concat all the files added below into 1 big .js file
	* which will help both in inital loading and
	* in caching, and in subsequent isUpdated 304 type requests for the files.
	**/
	

	/** static css **/
	files.add("/html/css/reset-fonts-grids.css");
	files.add("/html/css/base.css");
	files.add("/html/css/dot_admin.css");
	files.add("/html/js/calendar/calendar-blue.css");
	files.add("/html/css/font-awesome.min.css");

	/** 
	* Looks for plugins backend css to add
	**/
	String pluginsCSSPath = Config.getStringProperty("BACKEND_PLUGINS_CSS_PATHS");
	if(UtilMethods.isSet(pluginsCSSPath)){
		if(pluginsCSSPath.indexOf(",") != -1){
			for(String pluginCssPath : pluginsCSSPath.split(",")){
				files.add(pluginCssPath.trim());
			}
		}else{
			files.add(pluginsCSSPath.trim());
		}
	}
	
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
