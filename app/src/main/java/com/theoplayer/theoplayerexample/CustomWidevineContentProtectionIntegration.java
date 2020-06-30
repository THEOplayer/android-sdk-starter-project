package com.theoplayer.theoplayerexample;

import android.support.annotation.NonNull;

import com.theoplayer.android.api.contentprotection.CertificateRequestCallback;
import com.theoplayer.android.api.contentprotection.CertificateResponseCallback;
import com.theoplayer.android.api.contentprotection.ContentProtectionIntegration;
import com.theoplayer.android.api.contentprotection.LicenseRequestCallback;
import com.theoplayer.android.api.contentprotection.LicenseResponseCallback;
import com.theoplayer.android.api.contentprotection.Request;
import com.theoplayer.android.api.contentprotection.Response;
import com.theoplayer.android.api.source.drm.DRMConfiguration;

/**
 * An example implementation of ContentProtectionIntegration.
 */
public class CustomWidevineContentProtectionIntegration extends ContentProtectionIntegration {

    @NonNull
    private final DRMConfiguration configuration;

    public CustomWidevineContentProtectionIntegration(@NonNull DRMConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Allows for modifying / replacing the certificate request.
     *
     * The callback provides 2 options:
     * - Modify the request and call callback.request, THEOplayer will perform the actual network request.
     * - Retrieve the certificate and call callback.respond, THEOplayer will not perform a network request and will just use the provided certificate.
     *
     * In case an error occurs while you retrieve the certificate, call callback.error.
     */
    public void onCertificateRequest(Request request, CertificateRequestCallback callback) {
        // Update the url of the request
        request.setUrl(configuration.getWidevine().getLicenseAcquisitionURL());

        // The integration parameters passed to the source configuration can be retrieved here
        // and then can be used to modify the request
        String customHeaderValue = (String) this.configuration.getIntegrationParameters().get("custom-key");
        request.getHeaders().put("MY-DRM-HEADER", customHeaderValue);

        callback.request(request);
    }

    /**
     * Allows for modifying the certificate response.
     *
     * Given the response, retrieve the desired certificate and call callback.respond.
     * If some error occurs while retrieving the desired certificate, call callback.error.
     */
    public void onCertificateResponse(Response response, CertificateResponseCallback callback) {
        callback.respond(response.getBody());
    }

    /**
     * Allows for modifying / replacing the license request.
     *
     * The callback provides 2 options:
     * - Modify the request and call callback.request, THEOplayer will perform the actual network request.
     * - Retrieve the license and call callback.respond, THEOplayer will not perform a network request and will just use the provided license.
     *
     * In case an error occurs while you retrieve the certificate, use callback.error.
     */
    public void onLicenseRequest(Request request, LicenseRequestCallback callback) {
        callback.request(request);
    }

    /**
     * Allows for modifying the license response.
     *
     * Given the response, retrieve the desired license and call callback.respond.
     * If some error occurs while retrieving the desired license, call callback.error.
     */
    public void onLicenseResponse(Response response, LicenseResponseCallback callback) {
        callback.respond(response.getBody());
    }
}
