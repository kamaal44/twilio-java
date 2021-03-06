/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationRequest extends Resource {
    private static final long serialVersionUID = 163352133331334L;

    /**
     * Create a ValidationRequestCreator to execute create.
     *
     * @param pathAccountSid The SID of the Account responsible for the new Caller
     *                       ID
     * @param phoneNumber The phone number to verify in E.164 format
     * @return ValidationRequestCreator capable of executing the create
     */
    public static ValidationRequestCreator creator(final String pathAccountSid,
                                                   final com.twilio.type.PhoneNumber phoneNumber) {
        return new ValidationRequestCreator(pathAccountSid, phoneNumber);
    }

    /**
     * Create a ValidationRequestCreator to execute create.
     *
     * @param phoneNumber The phone number to verify in E.164 format
     * @return ValidationRequestCreator capable of executing the create
     */
    public static ValidationRequestCreator creator(final com.twilio.type.PhoneNumber phoneNumber) {
        return new ValidationRequestCreator(phoneNumber);
    }

    /**
     * Converts a JSON String into a ValidationRequest object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ValidationRequest object represented by the provided JSON
     */
    public static ValidationRequest fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ValidationRequest.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ValidationRequest object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ValidationRequest object represented by the provided JSON
     */
    public static ValidationRequest fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ValidationRequest.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String callSid;
    private final String friendlyName;
    private final com.twilio.type.PhoneNumber phoneNumber;
    private final String validationCode;

    @JsonCreator
    private ValidationRequest(@JsonProperty("account_sid")
                              final String accountSid,
                              @JsonProperty("call_sid")
                              final String callSid,
                              @JsonProperty("friendly_name")
                              final String friendlyName,
                              @JsonProperty("phone_number")
                              final com.twilio.type.PhoneNumber phoneNumber,
                              @JsonProperty("validation_code")
                              final String validationCode) {
        this.accountSid = accountSid;
        this.callSid = callSid;
        this.friendlyName = friendlyName;
        this.phoneNumber = phoneNumber;
        this.validationCode = validationCode;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Call the resource is associated with.
     *
     * @return The SID of the Call the resource is associated with
     */
    public final String getCallSid() {
        return this.callSid;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The phone number to verify in E.164 format.
     *
     * @return The phone number to verify in E.164 format
     */
    public final com.twilio.type.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns The 6 digit validation code that someone must enter to validate the
     * Caller ID  when `phone_number` is called.
     *
     * @return The 6 digit validation code that someone must enter to validate the
     *         Caller ID  when `phone_number` is called
     */
    public final String getValidationCode() {
        return this.validationCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidationRequest other = (ValidationRequest) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(callSid, other.callSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(phoneNumber, other.phoneNumber) &&
               Objects.equals(validationCode, other.validationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            callSid,
                            friendlyName,
                            phoneNumber,
                            validationCode);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("callSid", callSid)
                          .add("friendlyName", friendlyName)
                          .add("phoneNumber", phoneNumber)
                          .add("validationCode", validationCode)
                          .toString();
    }
}