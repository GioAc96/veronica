package rocks.gioac96.veronica.core;

import com.sun.net.httpserver.Headers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import rocks.gioac96.veronica.util.ArraySet;

/**
 * Http response.
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {

    @Getter
    @Setter
    @NonNull
    protected HttpStatus httpStatus;

    @Getter
    private byte[] body;

    @Getter
    @NonNull
    @Setter
    private Headers headers;

    @Getter
    @Setter
    private ArraySet<SetCookieHeader> cookies;

    protected Response(ResponseBuilder<?, ?> builder) {

        this.httpStatus = builder.httpStatus;
        this.body = builder.body;
        this.headers = builder.headers;
        this.cookies = builder.cookies;

    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public static ResponseBuilder<?, ?> builder() {

        return new ResponseBuilderImpl();

    }

    /**
     * Checks whether the response is already rendered.
     *
     * @return true iff the response is already rendered
     */
    public boolean isRendered() {

        return body != null;

    }

    /**
     * Writes the body of the response if the response is not already rendered.
     *
     * @param body body of the response
     * @return true if the response was not already rendered and the body was successfully written
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean writeBody(@NonNull String body) {

        return writeBody(body.getBytes());

    }

    /**
     * Writes the body of the response if the response is not already rendered.
     *
     * @param body body of the response
     * @return true if the response was not already rendered and the body was successfully written
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean writeBody(@NonNull byte[] body) {

        if (isRendered()) {

            return false;

        } else {

            this.body = body;

            return true;

        }

    }

    @Generated
    @SuppressWarnings({"checkstyle:MissingJavadocMethod", "checkstyle:MissingJavadocType"})
    public abstract static class ResponseBuilder<C extends Response, B extends ResponseBuilder<C, B>> {

        private final ArraySet<SetCookieHeader> cookies = new ArraySet<>();
        private HttpStatus httpStatus = HttpStatus.OK;
        private byte[] body = null;
        private Headers headers = new Headers();

        public B httpStatus(@NonNull HttpStatus httpStatus) {

            this.httpStatus = httpStatus;

            return self();

        }

        public B body(String body) {

            return body(body.getBytes());

        }

        public B body(byte[] body) {

            this.body = body;

            return self();

        }

        public B emptyBody() {

            return body("");

        }

        public B headers(@NonNull Headers headers) {

            this.headers = headers;

            return self();

        }

        public B requestBasicAuth(String realm) {

            if (realm == null) {

                return header("WWW-Authenticate", "Basic");

            } else {

                return header("WWW-Authenticate", "Basic realm=\"" + realm + '\"');

            }

        }

        public B requestBasicAuth() {

            return requestBasicAuth(null);

        }

        @SuppressWarnings("unused")
        public B header(@NonNull String key, @NonNull String value) {

            if (this.headers.containsKey(key)) {

                this.headers.get(key).add(value);

            } else {

                this.headers.put(key, List.of(value));

            }

            return self();

        }

        @SuppressWarnings({"checkstyle:RightCurly", "checkstyle:Indentation", "unused"})
        public B header(@NonNull String key, @NonNull Collection<String> values) {

            if (this.headers.containsKey(key)) {

                this.headers.get(key).addAll(values);

            } else {

                this.headers.put(key, new ArrayList<>() {{
                    addAll(values);
                }});

            }

            return self();

        }

        @SuppressWarnings("unused")
        public B cookies(Collection<SetCookieHeader> cookies) {

            this.cookies.addAll(cookies);

            return self();

        }

        @SuppressWarnings("unused")
        public B cookie(@NonNull SetCookieHeader cookie) {

            this.cookies.add(cookie);

            return self();

        }

        protected abstract B self();

        public abstract C build();

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ResponseBuilderImpl extends ResponseBuilder<Response, ResponseBuilderImpl> {

        protected Response.ResponseBuilderImpl self() {

            return this;

        }

        @SuppressWarnings("unused")
        public Response build() {

            return new Response(this);

        }

    }

}