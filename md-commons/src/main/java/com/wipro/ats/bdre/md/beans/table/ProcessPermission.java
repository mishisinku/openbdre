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

package com.wipro.ats.bdre.md.beans.table;

import javax.validation.constraints.*;

/**
 * Created by MI294210 on 06-01-2016.
 */

/**
 * This class contains all the setter and getter methods for ProcessPermissions fields.
 */
public class ProcessPermission {

    @NotNull
    private Integer processId;
    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    private Integer role;
    @NotNull
    @Pattern(regexp = "[0-z]+")
    @Size(max = 45)
    private String  owner;
    private Boolean ownerR;
    private Boolean ownerW;
    private Boolean ownerX;
    private Boolean groupR;
    private Boolean groupW;
    private Boolean groupX;
    private Boolean otherR;
    private Boolean otherW;
    private Boolean otherX;
    private Integer pageSize;
    private Integer counter;
    private Integer page;

    @Override
    public String toString() {
        return " permissionId:" + processId+"owner:"+owner +"RoleId:"+role+"ownerR:"+ownerR +"ownerW:"+ownerW +"ownerR:"+ownerX+"groupR:" +groupR+ "groupW:" +groupW+"groupX:" +groupX+"otherR:"+otherR+"otherW:"+otherW+"otherX:"+otherX+"Counter:"+counter+
                " page:" + page;
    }


    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer permissionId) {
        this.processId = permissionId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean getOwnerR() {
        return ownerR;
    }

    public void setOwnerR(Boolean ownerR) {
        this.ownerR = ownerR;
    }

    public Boolean getOwnerW() {
        return ownerW;
    }

    public void setOwnerW(Boolean ownerW) {
        this.ownerW = ownerW;
    }

    public Boolean getOwnerX() {
        return ownerX;
    }

    public void setOwnerX(Boolean ownerX) {
        this.ownerX = ownerX;
    }

    public Boolean getGroupR() {
        return groupR;
    }

    public void setGroupR(Boolean groupR) {
        this.groupR = groupR;
    }

    public Boolean getGroupW() {
        return groupW;
    }

    public void setGroupW(Boolean groupW) {
        this.groupW = groupW;
    }

    public Boolean getGroupX() {
        return groupX;
    }

    public void setGroupX(Boolean groupX) {
        this.groupX = groupX;
    }

    public Boolean getOtherR() {
        return otherR;
    }

    public void setOtherR(Boolean otherR) {
        this.otherR = otherR;
    }

    public Boolean getOtherW() {
        return otherW;
    }

    public void setOtherW(Boolean otherW) {
        this.otherW = otherW;
    }

    public Boolean getOtherX() {
        return otherX;
    }

    public void setOtherX(Boolean otherX) {
        this.otherX = otherX;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
