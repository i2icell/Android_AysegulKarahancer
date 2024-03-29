package com.example.i2iCell;

import com.example.i2iCell.ui.login.User;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WebService {

    public class WebServiceClient {


        private final String LOGIN_SUCCESSFUL = "1";
        private final String RETURN_PATTERN = "<ns:return>([^>]*)(?=.*</ns:return>)";
        private final String RETURN_TAG_BEGIN = "<ns:return>";
        private final String RETURN_TAG_END = "</ns:return";

        public String requestFromUrl(String serviceUrlString) {

            StringBuilder responseStringBuilder = new StringBuilder();
            URL serviceUrl;
            try {
                serviceUrl = new URL(serviceUrlString);
                URLConnection serviceConnection = serviceUrl.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serviceConnection.getInputStream()));

                String lineOfResponse;
                while ((lineOfResponse = bufferedReader.readLine()) != null) {
                    responseStringBuilder.append(lineOfResponse + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                //log.error("ERROR AT WEB SERVICE CLIENT - login method : " + e);

            }

            return responseStringBuilder.toString();
        }

        private boolean getBooleanResultFromResponse(String response) {

            int beginIndex = response.indexOf(RETURN_TAG_BEGIN) + RETURN_TAG_BEGIN.length();
            int endIndex = response.indexOf(RETURN_TAG_END);

            String result = response.substring(beginIndex, endIndex);

            if (result.equals(LOGIN_SUCCESSFUL))
                return true;
            return false;

        }

        public String[] getStringResultFromResponse(String response) {

            int endIndex,beginIndex;

            Pattern pattern = Pattern.compile(RETURN_PATTERN);
            Matcher matcher = pattern.matcher(response);

            String resultWord = "";
            String rawResult;

            while (matcher.find()) {

                rawResult = matcher.group();
                beginIndex = rawResult.indexOf(RETURN_TAG_BEGIN  ) + RETURN_TAG_BEGIN.length()  ;
                if(rawResult.contains(RETURN_TAG_END)) {
                    endIndex  = rawResult.indexOf(RETURN_TAG_END);
                }
                else {
                    endIndex =  rawResult.length() ;
                }
                resultWord += rawResult.substring(beginIndex,endIndex) + ",";
            }

            String[] resultArray = resultWord.split(",");
            return resultArray;

        }


        public boolean login(String userName, String password) {


            String serviceUrlString =	readServiceUrlFromFile("login");
            serviceUrlString = serviceUrlString.replace("USERNAME", userName);
            serviceUrlString = serviceUrlString.replace("PASSWORDINPUT", password);



            if(getStringResultFromResponse(requestFromUrl(serviceUrlString))[0].equals(LOGIN_SUCCESSFUL) )
                return true;
            return false;

            //return getSingleResultFromResponse(requestFromUrl(serviceUrlString));
        }



        public String[] getBalances(String phoneNumber) {

            String serviceUrlString =	readServiceUrlFromFile("getBalances");
            serviceUrlString = serviceUrlString.replace("INPUTNUMBER", phoneNumber);

            String response = requestFromUrl(serviceUrlString);
            String[] balances = getStringResultFromResponse(response);

            return balances;
        }

        public boolean createUser(User user) {

            String serviceUrlString =	readServiceUrlFromFile("createUser");
            serviceUrlString = insertUserInputsToString(user, serviceUrlString);

            return getBooleanResultFromResponse(requestFromUrl(serviceUrlString));
        }

        public boolean changePassword(User user) {

            String serviceUrlString = readServiceUrlFromFile("changePassword");

            serviceUrlString  =  serviceUrlString.replace("TCNUMBERINPUT", user.getTcNumber());
            serviceUrlString  =  serviceUrlString.replace("USERNAME", user.getPhoneNumber());
            serviceUrlString  =  serviceUrlString.replace("PASSWORDINPUT", user.getPassword());

            String response = requestFromUrl(serviceUrlString);

            return getBooleanResultFromResponse(response);

        }


        private String readServiceUrlFromFile(String webServiceName) {

            File file = new File("src/i2iCell//ServiceLinks.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;

            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);
                String urlString = document.getElementsByTagName(webServiceName).item(0).getTextContent();
                return urlString;
            } catch (Exception e) {
                //log.error("Error while XML file reading: " + e);
                return null;
            }

        }

        private String insertUserInputsToString(User user, String serviceUrlString) {

            serviceUrlString = serviceUrlString.replace("FIRSTNAMEINPUT", user.getFirstName());
            serviceUrlString = serviceUrlString.replace("LASTNAMEINPUT", user.getLastName());
            serviceUrlString = serviceUrlString.replace("PHONENUMBERINPUT", user.getPhoneNumber());
            serviceUrlString = serviceUrlString.replace("EMAILINPUT", user.getMail());
            serviceUrlString = serviceUrlString.replace("PASSWORDINPUT", user.getPassword());
            serviceUrlString = serviceUrlString.replace("BIRTHDAYINPUT", user.getBirthDate());
            serviceUrlString = serviceUrlString.replace("TCNUMBERINPUT", user.getTcNumber());

            return serviceUrlString;


        }

    }



















}
