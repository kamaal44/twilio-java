/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class VerificationCheckCreator extends Creator<VerificationCheck> {
    private final String pathServiceSid;
    private final String code;
    private String to;
    private String verificationSid;
    private String amount;
    private String payee;

    /**
     * Construct a new VerificationCheckCreator.
     *
     * @param pathServiceSid The SID of the verification Service to create the
     *                       resource under
     * @param code The verification string
     */
    public VerificationCheckCreator(final String pathServiceSid,
                                    final String code) {
        this.pathServiceSid = pathServiceSid;
        this.code = code;
    }

    /**
     * The phone number or [email](https://www.twilio.com/docs/verify/email) to
     * verify. Either this parameter or the `verification_sid` must be specified.
     * Phone numbers must be in [E.164
     * format](https://www.twilio.com/docs/glossary/what-e164)..
     *
     * @param to The phone number or email to verify
     * @return this
     */
    public VerificationCheckCreator setTo(final String to) {
        this.to = to;
        return this;
    }

    /**
     * A SID that uniquely identifies the Verification Check. Either this parameter
     * or the `to` phone number/[email](https://www.twilio.com/docs/verify/email)
     * must be specified..
     *
     * @param verificationSid A SID that uniquely identifies the Verification Check
     * @return this
     */
    public VerificationCheckCreator setVerificationSid(final String verificationSid) {
        this.verificationSid = verificationSid;
        return this;
    }

    /**
     * The amount of the associated PSD2 compliant transaction. Requires the PSD2
     * Service flag enabled..
     *
     * @param amount The amount of the associated PSD2 compliant transaction.
     * @return this
     */
    public VerificationCheckCreator setAmount(final String amount) {
        this.amount = amount;
        return this;
    }

    /**
     * The payee of the associated PSD2 compliant transaction. Requires the PSD2
     * Service flag enabled..
     *
     * @param payee The payee of the associated PSD2 compliant transaction
     * @return this
     */
    public VerificationCheckCreator setPayee(final String payee) {
        this.payee = payee;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created VerificationCheck
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public VerificationCheck create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VERIFY.toString(),
            "/v2/Services/" + this.pathServiceSid + "/VerificationCheck"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("VerificationCheck creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return VerificationCheck.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (code != null) {
            request.addPostParam("Code", code);
        }

        if (to != null) {
            request.addPostParam("To", to);
        }

        if (verificationSid != null) {
            request.addPostParam("VerificationSid", verificationSid);
        }

        if (amount != null) {
            request.addPostParam("Amount", amount);
        }

        if (payee != null) {
            request.addPostParam("Payee", payee);
        }
    }
}
