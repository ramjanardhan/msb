/*
 * @(#)LoginAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */
package com.mss.msp.general;

import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.usersdata.UserAddress;
//import com.mss.msp.usr.miscellaneous.UsrMiscellaneousVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.Iterator;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.Properties;
import java.sql.PreparedStatement;
import com.mss.msp.util.ServiceLocatorException;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;
import com.mss.msp.util.SecurityProperties;
import com.mss.msp.util.ServiceLocator;
import java.io.File;
import java.util.Date;
import org.apache.struts2.ServletActionContext;

/**
 * The
 * <code>LoginAction</code> class is used for to enter in to MirageV2 from
 * <i>Login.jsp</i> page.
 *
 * @author Prasad kandregula <a
 * href="mailto:vkandregula@miraclesoft.com">vkandregula@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ApplicationConstants
 * @see com.mss.mirage.util.HibernateServiceLocator;
 * @see com.mss.mirage.util.PasswordUtility;
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /*@param resultType used to store type of the result*/
    private String resultType;
    private String emailId;
    private String password;
    private String redirectAction;
    private String resultMessage;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private HttpSession httpSession;
    private String consultant_login;
    private UserAddress userAddress;
    private int org_id;

    /**
     * Creates a new instance of LoginAction
     */
    public LoginAction() {
    }

    /**
     * execute() method ,in this method checking user details means he is
     * authenticated persion or not, user enter loginId and password in login
     * page ,those details compare with database details, loginId and password
     * equal with database details then he can login success fully other wise he
     * will get some message.
     */
    public String execute() throws Exception {
        System.out.println("********************LoginAction :: execute Action Start*********************");
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String dbCurStatus = "";
        int dbUserId = 0;
        String dbLoginId = "";
        String dbPassword = "";
        String dbsalt = "";
        String dbTypeOfUsr = "";
        String dbFirstName = "";
        String dbLastName = "";
        String dbPhone = "";
        String dbDOB = "";
        int dbOrgId = 0;
        String dbLivingCountry = "";
        String dbWorkingCountry = "";
        String dbEmail = "";
        String dbProfilImagePath = "";
        String dbmstatus = "";
        String dbalias = "";
        String dbgender = "";
        int dbIsTeamLead = 0;
        int dbIsManager = 0;
        String dbAccStatus = "";
        try {
          
            connection = ConnectionProvider.getInstance().getConnection();
         
            statement = connection.createStatement();
            HttpSession session = getHttpServletRequest().getSession(true);

         
            if (DataSourceDataProvider.getInstance().getOrgIdByEmailExt(getEmailId().toLowerCase().trim()) > 0) {
                String SQL_QUERY = "SELECT u.usr_id as usrId,login_id,salt,PASSWORD,type_of_user,first_name,last_name,"
                        + "dob,cur_status,living_country,working_country,org_id,email1,image_path,phone1,marital_status,alias_name,gender,acc.status FROM users u left outer join usr_reg ur on(u.usr_id=ur.usr_id) "
                        + " LEFT OUTER JOIN accounts acc ON (org_id=acc.account_id) WHERE acc.STATUS='Active' and login_id like '" + getEmailId().toLowerCase().trim() + "'";
                System.out.println("SQL_QUERY-->" + SQL_QUERY);
                resultSet = statement.executeQuery(SQL_QUERY.toString());
                while (resultSet.next()) {
                    dbUserId = resultSet.getInt("usrId");
                    dbLoginId = resultSet.getString("login_id");
                    dbgender = resultSet.getString("gender");
                    dbsalt = resultSet.getString("salt");
                    dbPassword = resultSet.getString("PASSWORD");
                    System.out.println("this is type if type of user--->" + resultSet.getString("type_of_user"));
                    dbTypeOfUsr = resultSet.getString("type_of_user");
                    System.out.println("this is type if first_name--->" + resultSet.getString("first_name"));
                    dbFirstName = resultSet.getString("first_name");
                    System.out.println("this is type last name--->" + resultSet.getString("last_name"));
                    dbLastName = resultSet.getString("last_name");


                    if ((resultSet.getTimestamp("dob") != null)) {
                        System.out.println("i am in if condition");
                        dbDOB = resultSet.getTimestamp("dob").toString();
                    }
                
                  
                    dbCurStatus = resultSet.getString("cur_status");
                    if (resultSet.getString("living_country") != null) {
                        dbLivingCountry = com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("living_country"));
                    }
                    if (resultSet.getString("working_country") != null) {

                        dbWorkingCountry = com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("working_country"));
                    }
                    dbOrgId = resultSet.getInt("org_id");
                    dbEmail = resultSet.getString("email1");
                    dbPhone = resultSet.getString("phone1");
                    dbProfilImagePath = resultSet.getString("image_path");

                    //marital_status,alias_name
                 
                    if (resultSet.getString("marital_status") != null) {
                        dbmstatus = resultSet.getString("marital_status");
                    }
                    if (resultSet.getString("alias_name") != null) {

                        dbalias = resultSet.getString("alias_name");
                    }
                    dbAccStatus = resultSet.getString("status");
                }

                String encPwd = SecurityServiceProvider.getEncrypt(getPassword().trim(), dbsalt.trim());
            
                if (!"In-Active".equalsIgnoreCase(dbAccStatus)) {
                    if ("Registered".equalsIgnoreCase(dbCurStatus)) {
                        getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Sorry! Your account will be activated soon!</font>");
                        resultType = INPUT;
                    } else {
                        if (encPwd.equalsIgnoreCase(dbPassword.trim())) {
                            if ("Active".equalsIgnoreCase(dbCurStatus)) {
                                session.setAttribute(ApplicationConstants.USER_ID, dbUserId);
                                session.setAttribute(ApplicationConstants.LOGIN_ID, dbLoginId);
                                session.setAttribute(ApplicationConstants.TYPE_OF_USER, dbTypeOfUsr);
                                session.setAttribute(ApplicationConstants.FIRST_NAME, dbFirstName);
                                session.setAttribute(ApplicationConstants.Last_NAME, dbLastName);
                                if (dbProfilImagePath != null) {
                                    File imageFile = new File(dbProfilImagePath);
                                    if (imageFile.exists() == false) {
                                        if ("F".equals(dbgender)) {
                                            session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, Properties.getProperty("Profile.FEMALEIMAGE"));

                                        } else {
                                            session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, Properties.getProperty("Profile.GENERALIMAGE"));
                                        }
                                    } else {
                                        session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, dbProfilImagePath);
                                    }
                                } else {
                                    if ("F".equals(dbgender)) {
                                        session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, Properties.getProperty("Profile.FEMALEIMAGE"));

                                    } else {
                                        session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, Properties.getProperty("Profile.GENERALIMAGE"));
                                    }
                                }
                                session.setAttribute(ApplicationConstants.MSTATUS, dbmstatus);
                                session.setAttribute(ApplicationConstants.AliasName, dbalias);
                                session.setAttribute(ApplicationConstants.GENDER, dbgender);
                                session.setAttribute(ApplicationConstants.DOB, dbDOB);
                                session.setAttribute(ApplicationConstants.PHONE, dbPhone);
                                session.setAttribute(ApplicationConstants.ORG_ID, dbOrgId);
                                session.setAttribute(ApplicationConstants.LIVING_COUNTRY, dbLivingCountry);
                                session.setAttribute(ApplicationConstants.WORK_COUNTRY, dbWorkingCountry);
                                session.setAttribute(ApplicationConstants.EMAIL, dbEmail);
                           
                                String primaryRoledetails = DataSourceDataProvider.getInstance().getUsrPrimaryRole(dbUserId);
                           
                                String[] parts = primaryRoledetails.split("#");
                                String part1 = parts[0]; // 004
                                String part2 = parts[1];
                                int primaryRole = Integer.parseInt(part1);
                          
                                session.setAttribute(ApplicationConstants.PRIMARYROLE, primaryRole);
                                session.setAttribute(ApplicationConstants.PRIMARYROLEVALUE, part2);
                                session.setAttribute(ApplicationConstants.ROLESMAP, DataSourceDataProvider.getInstance().getUsrRolesMap(dbUserId));
                                session.setAttribute(ApplicationConstants.SKILLS_MAP, DataSourceDataProvider.getInstance().getReqSkillsCategory(1));
                                int usrGrpId = DataSourceDataProvider.getInstance().getCategoryByUserId(dbUserId);
                                session.setAttribute(ApplicationConstants.USER_GROUP_ID, usrGrpId);
                                if (usrGrpId > 0) {
                                    session.setAttribute(ApplicationConstants.USER_CATEGORY_LIST, DataSourceDataProvider.getInstance().getUserSubCategoryByUsrId(dbUserId));
                                }


                                System.out.println("In login action" + ApplicationConstants.TYPE_OF_USER);
                                System.out.println("In login action Skills" + ApplicationConstants.SKILLS_MAP);

                                  String resactionName = "";
                                resactionName = SecurityServiceProvider.doRedirect(dbOrgId, dbTypeOfUsr, primaryRole);
                                if (resactionName.equals("../general/logout.action")) {
                                    getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Sorry Your Account is InActive! </font>");
                                    resultType = INPUT;
                                }
                                if (resactionName.equals("")) {
                                    resactionName = SecurityServiceProvider.doRedirect(0, dbTypeOfUsr, primaryRole);
                                }

                              
                                System.err.println("resactionName==>" + resactionName);
                                if (!resactionName.equals("")) {
                                    setRedirectAction(resactionName);
                                } else {
                                    setResultMessage("Please contact Support team!");
                                    setRedirectAction("../general/regerrorDirect.action?resultMessage=" + getResultMessage());
                                }


                                resultType = SUCCESS;

                            } else {
                                getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Sorry Your Account is InActive! </font>");
                                resultType = INPUT;
                            }
                        } else {
                          
                            getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                            resultType = INPUT;
                        }
                    }
                } else {
                    getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Oraganization record not existed,please contact Admin team!</font>");
                    resultType = INPUT;
                }

            }
            




            if (resultType == null) {
                setRedirectAction("../recruitment/consultantLogin/consultant_login.jsp");
                getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Please Login with valid UserId and Password! </font>");
                resultType = INPUT;
            }
        } catch (Exception ex) {
          
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
         

            setRedirectAction("../general/regerrorDirect.action?resultMessage=" + getResultMessage());
            resultType = ERROR;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                throw new Exception(sqle);
            }
        }

        if (resultType.equalsIgnoreCase("SUCCESS")) {
            logUserAccess();
       


        }
         System.out.println("********************LoginAction :: execute Action End*********************");
        return resultType;
    }//end of the execute method

    /**
     * doLogout() method is used to invalidate session
     */
    public String doLogout() throws Exception {
       
        System.out.println("********************LoginAction :: doLogout Action start*********************");
        try {
            if (getHttpServletRequest().getSession(false) != null) {
                getHttpServletRequest().getSession(false).invalidate();


            }
            resultType = SUCCESS;
        } catch (Exception ex) {
           
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************LoginAction :: doLogout Action End*********************");
        return resultType;
    }

     /**
     *********************************************
     *
     * @accessDeneid() to denie the  action 
     * 
     *
     *********************************************
     */
    public String accessDeneid() throws Exception {
         System.out.println("********************LoginAction :: accessDeneid Action start*********************");
      
        getHttpServletRequest().setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Access is denied!. Please Login. </font>");
         System.out.println("********************LoginAction :: accessDeneid Action End*********************");
        return SUCCESS;
    }

     /**
     *****************************************
     *
     * @logUserAccess() to access the  user 
     * 
     *
     *****************************************
     */
    public void logUserAccess() throws Exception {
          System.out.println("********************LoginAction :: logUserAccess Action start*********************");
        try {
            if (getHttpServletRequest().getSession(false) != null) {
                if (getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.LOGIN_ID) != null) {
                    String UserId = getHttpServletRequest().getSession(false).getAttribute(ApplicationConstants.LOGIN_ID).toString();
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String via = httpServletRequest.getHeader("VIA");
                    String remote = httpServletRequest.getRemoteAddr();
                    String agent = httpServletRequest.getHeader("User-Agent");
                    Timestamp accessedtime = (DateUtility.getInstance().getCurrentMySqlDateTime());
                    Connection connection = null;
                    PreparedStatement preparedStatement = null;
                   
                    boolean isInserted = false;
                    String query = null;
                    try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        query = "insert into log_user_access(loginId,x_forwarded_for1,via, remote_addr,user_agent,date_accessed) values(?,?,?,?,?,?)";
                        preparedStatement = connection.prepareStatement(query);

                        preparedStatement.setString(1, UserId);
                        preparedStatement.setString(2, forwarded);
                        preparedStatement.setString(3, via);
                        preparedStatement.setString(4, remote);
                        preparedStatement.setString(5, agent);
                        preparedStatement.setTimestamp(6, accessedtime);
                        int x = preparedStatement.executeUpdate();
                        preparedStatement.close();
                        if (x > 0) {
                            isInserted = true;
                        }
                    } catch (SQLException sql) {
                        sql.printStackTrace();
                        throw new ServiceLocatorException(sql);
                    } finally {
                        try {
                            if (preparedStatement != null) {
                                preparedStatement.close();
                                preparedStatement = null;
                            }
                            if (connection != null) {
                                connection.close();
                                connection = null;
                            }
                        } catch (SQLException sqle) {
                            throw new ServiceLocatorException(sqle);
                        }
                    }

                }
            }
        } catch (Exception ex) {
          
            ex.printStackTrace();
            getHttpServletRequest().getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************LoginAction :: logUserAccess Action End*********************");
        //   return resultType;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     *
     * This method is used to set the Servlet Response
     *
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     *
     * This method is used to set the Servlet Request
     *
     * @param httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the redirectAction
     */
    public String getRedirectAction() {
        return redirectAction;
    }

    /**
     * @param redirectAction the redirectAction to set
     */
    public void setRedirectAction(String redirectAction) {
        this.redirectAction = redirectAction;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getConsultant_login() {
        return consultant_login;
    }

    public void setConsultant_login(String consultant_login) {
        this.consultant_login = consultant_login;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }
}
