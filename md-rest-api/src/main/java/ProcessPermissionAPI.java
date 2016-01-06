/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.wipro.ats.bdre.md.api.base.MetadataAPIBase;
import com.wipro.ats.bdre.md.beans.table.ProcessPermission;
import com.wipro.ats.bdre.md.beans.table.Users;
import com.wipro.ats.bdre.md.dao.ProcessDAO;
import com.wipro.ats.bdre.md.dao.ProcessPermissionDAO;
import com.wipro.ats.bdre.md.dao.UserRolesDAO;
import com.wipro.ats.bdre.md.dao.UsersDAO;
import com.wipro.ats.bdre.md.rest.RestWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leela on 26-02-2015.
 */

@Controller
@RequestMapping("/processpermission")
public class ProcessPermissionAPI extends MetadataAPIBase {
    private static final Logger LOGGER = Logger.getLogger(ProcessPermissionAPI.class);
    @Autowired
    ProcessPermissionDAO processPermissionDAO;
@Autowired
    ProcessDAO processDAO;
    /**
     * This method calls proc GetUsers and returns the details of process permission for process Id  passed.
     *
     * @param processId
     * @return restWraper Instance of ProcessPermission for process Id  passed.
     */
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public
    @ResponseBody
    RestWrapper get(
            @PathVariable("pid") Integer processId, Principal principal
    ) {

        RestWrapper restWrapper = null;
        try {
            com.wipro.ats.bdre.md.dao.jpa.ProcessPermission jpaProcessPermission = processPermissionDAO.get(processId);
            com.wipro.ats.bdre.md.beans.table.ProcessPermission processPermission = new ProcessPermission();
            if (jpaProcessPermission != null) {
                processPermission.setProcessId(jpaProcessPermission.getProcess().getProcessId);
                processPermission.setOwner(jpaProcessPermission.getUsers().getUsername());
                processPermission.setRole(jpaProcessPermission.getUserRoles().getUserRoleId());
                processPermission.setOwnerR(jpaProcessPermission.getOwnerR());
                processPermission.setOwnerW(jpaProcessPermission.getOwnerW());
                processPermission.setOwnerX(jpaProcessPermission.getOwnerX());
                processPermission.setGroupR(jpaProcessPermission.getGroupR());
                processPermission.setGroupW(jpaProcessPermission.getGroupW());
                processPermission.setGroupX(jpaProcessPermission.getGroupX());
                processPermission.setOtherR(jpaProcessPermission.getOtherR());
                processPermission.setOtherW(jpaProcessPermission.getOtherW());
                processPermission.setOtherX(jpaProcessPermission.getOtherX());

            }

            restWrapper = new RestWrapper(processPermission, RestWrapper.OK);
            LOGGER.info("Record with process Id:" + processId + " selected from ProcessPermission by User:" + principal.getName());

        } catch (Exception e) {
            restWrapper = new RestWrapper(e.getMessage(), RestWrapper.ERROR);
        }
        return restWrapper;
    }

    /**
     * This method  removes record of process permission for process Id passed.
     *
     * @param processId
     * @param model
     * @return nothing.
     */
    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    RestWrapper delete(
            @PathVariable("pid") Integer processId, Principal principal,
            ModelMap model) {

        RestWrapper restWrapper = null;
        try {

            processPermissionDAO.delete(processId);
            // s.delete("call_procedures.DeleteUsers", users);

            restWrapper = new RestWrapper(null, RestWrapper.OK);
            LOGGER.info("Record with id:" + processId + " deleted from ProcessPermission by User:" + principal.getName());

        } catch (Exception e) {
            restWrapper = new RestWrapper(e.getMessage(), RestWrapper.ERROR);
        }
        return restWrapper;
    }

    /**
     * This method calls proc ListUsers and returns a list of instances of ProcesssPermission.
     *
     * @param
     * @return restWrapper It contains a list of instances of ProcessPermission.
     */

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)

    public
    @ResponseBody
    RestWrapper list(@RequestParam(value = "page", defaultValue = "0") int startPage,
                     @RequestParam(value = "size", defaultValue = "10") int pageSize, Principal principal) {

        RestWrapper restWrapper = null;
        try {
            Integer counter=processPermissionDAO.totalRecordCount();
            List<com.wipro.ats.bdre.md.dao.jpa.ProcessPermission> jpaProcessPermissionList = processPermissionDAO.list(startPage, pageSize);
            List<ProcessPermission> processPermissionList = new ArrayList<ProcessPermission>();
            for (com.wipro.ats.bdre.md.dao.jpa.ProcessPermission jpaProcessPermission : jpaProcessPermissionList) {
                com.wipro.ats.bdre.md.beans.table.ProcessPermission processPermission = new ProcessPermission();
                processPermission.setProcessId(jpaProcessPermission.getProcess().getProcessId());
                processPermission.setOwner(jpaProcessPermission.getUsers().getUsername());
                processPermission.setRole(jpaProcessPermission.getUserRoles().getUserRoleId());
                processPermission.setOwnerR(jpaProcessPermission.getOwnerR());
                processPermission.setOwnerW(jpaProcessPermission.getOwnerW());
                processPermission.setOwnerX(jpaProcessPermission.getOwnerX());
                processPermission.setGroupR(jpaProcessPermission.getGroupR());
                processPermission.setGroupW(jpaProcessPermission.getGroupW());
                processPermission.setGroupX(jpaProcessPermission.getGroupX());
                processPermission.setOtherR(jpaProcessPermission.getOtherR());
                processPermission.setOtherW(jpaProcessPermission.getOtherW());
                processPermission.setOtherX(jpaProcessPermission.getOtherX());
                processPermission.setCounter(counter);
                processPermissionList.add(processPermission);

            }

            restWrapper = new RestWrapper(processPermissionList, RestWrapper.OK);
            LOGGER.info("All records listed from ProcessPermission by User:" + principal.getName());

        } catch (Exception e) {
            restWrapper = new RestWrapper(e.getMessage(), RestWrapper.ERROR);
        }
        return restWrapper;
    }

    @Autowired
    UsersDAO usersDAO;
    @Autowired
    UserRolesDAO userRolesDAO;

    /**
     * This method  updates the values of the instance of ProcessPermission passed. It also
     * validates the values passed.
     *
     * @param processPermission        Instance of ProcessPermission.
     * @param bindingResult
     * @return restWrapper It contains the updated instance of ProcessPermission passed.
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public
    @ResponseBody
    RestWrapper update(@ModelAttribute("processpermission")
                       @Valid ProcessPermission processPermission, BindingResult bindingResult, Principal principal) {
        RestWrapper restWrapper = null;
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("<p>Please fix following errors and try again<p><ul>");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages.append("<li>");
                errorMessages.append(error.getField());
                errorMessages.append(". Bad value: '");
                errorMessages.append(error.getRejectedValue());
                errorMessages.append("'</li>");
            }
            errorMessages.append("</ul>");
            restWrapper = new RestWrapper(errorMessages.toString(), RestWrapper.ERROR);
            return restWrapper;
        }
        try {
            com.wipro.ats.bdre.md.dao.jpa.ProcessPermission jpaProcessPermission = new com.wipro.ats.bdre.md.dao.jpa.ProcessPermission();
            jpaProcessPermission.setProcess(processDAO.get(processPermission.getProcessId()));
            jpaProcessPermission.setUsers(usersDAO.get(processPermission.getOwner()));
            jpaProcessPermission.setUserRoles(userRolesDAO.get(processPermission.getRole()));
            jpaProcessPermission.setOwnerR(processPermission.getOwnerR());
            jpaProcessPermission.setOwnerW(processPermission.getOwnerW());
            jpaProcessPermission.setOwnerX(processPermission.getOwnerX());
            jpaProcessPermission.setGroupR(processPermission.getGroupR());
            jpaProcessPermission.setGroupW(processPermission.getGroupW());
            jpaProcessPermission.setGroupX(processPermission.getGroupX());
            jpaProcessPermission.setOtherR(processPermission.getOtherR());
            jpaProcessPermission.setOtherW(processPermission.getOtherW());
            jpaProcessPermission.setOtherX(processPermission.getOtherX());


            processPermissionDAO.update(jpaProcessPermission);


            restWrapper = new RestWrapper(processPermission, RestWrapper.OK);
            LOGGER.info("Record with process Id:" + processPermission.getProcessId() + " updated in Users by User:" + principal.getName() + processPermission);

        } catch (Exception e) {
            restWrapper = new RestWrapper(e.getMessage(), RestWrapper.ERROR);
        }

        return restWrapper;
    }

    /**
     * This method adds a record passed in ProcessPermission table. It also validates the values passed.
     *
     * @param processPermission        Instance of ProcessPermission.
     * @param bindingResult
     * @return restWrapper Instance of ProcessPermission newly added.
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.PUT)
    public
    @ResponseBody
    RestWrapper insert(@ModelAttribute("processpermission")
                       @Valid ProcessPermission processPermission, BindingResult bindingResult, Principal principal) {

        RestWrapper restWrapper = null;
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("<p>Please fix following errors and try again<p><ul>");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages.append("<li>");
                errorMessages.append(error.getField());
                errorMessages.append(". Bad value: '");
                errorMessages.append(error.getRejectedValue());
                errorMessages.append("'</li>");
            }
            errorMessages.append("</ul>");
            restWrapper = new RestWrapper(errorMessages.toString(), RestWrapper.ERROR);
            return restWrapper;
        }
        try {
            com.wipro.ats.bdre.md.dao.jpa.ProcessPermission jpaProcessPermission = new com.wipro.ats.bdre.md.dao.jpa.ProcessPermission();
            jpaProcessPermission.setProcess(processDAO.get(processPermission.getProcessId()));
            jpaProcessPermission.setUsers(usersDAO.get(processPermission.getOwner()));
            jpaProcessPermission.setUserRoles(userRolesDAO.get(processPermission.getRole()));
            jpaProcessPermission.setOwnerR(processPermission.getOwnerR());
            jpaProcessPermission.setOwnerW(processPermission.getOwnerW());
            jpaProcessPermission.setOwnerX(processPermission.getOwnerX());
            jpaProcessPermission.setGroupR(processPermission.getGroupR());
            jpaProcessPermission.setGroupW(processPermission.getGroupW());
            jpaProcessPermission.setGroupX(processPermission.getGroupX());
            jpaProcessPermission.setOtherR(processPermission.getOtherR());
            jpaProcessPermission.setOtherW(processPermission.getOtherW());
            jpaProcessPermission.setOtherX(processPermission.getOtherX());


            processPermissionDAO.insert(jpaProcessPermission);


            restWrapper = new RestWrapper(processPermission, RestWrapper.OK);
            LOGGER.info("Record with process Id:" + processPermission.getProcessId() + " updated in Users by User:" + principal.getName() + processPermission);

        } catch (Exception e) {
            restWrapper = new RestWrapper(e.getMessage(), RestWrapper.ERROR);
        }
        return restWrapper;
    }


    @Override
    public Object execute(String[] params) {
        return null;
    }
}
