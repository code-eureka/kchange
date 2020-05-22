package com.kchange.services.beans;

import com.kchange.common.model.Device;
import com.kchange.common.model.StatusCode;

import java.util.List;
import java.util.UUID;

public class UserProfile {
    String userId;
    String firstName;
    String lastName;
    String logonId;
    String clearPassword;
    String emailAddress;
    String phoneNumber;
    String gender;
    StatusCode status;
    List<Device> authorizedDevices;

    public UserProfile(String firstName, String lastName, String logonId, String clearPassword, String emailAddress, String phoneNumber, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.logonId = logonId;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.clearPassword = clearPassword;
        this.userId = UUID.randomUUID().toString();
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClearPassword() {
        return clearPassword;
    }

    public StatusCode getStatus() {
        return status;
    }

    public List<Device> getAuthorizedDevices() {
        return authorizedDevices;
    }
}
