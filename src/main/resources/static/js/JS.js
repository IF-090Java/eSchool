var host='/';
var TOKEN_KEY = "jwtToken"
function getJwtToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function validateUserPermissions() {
    $(document).ready(function () {
        var jwtToken = getJwtToken();
        if (jwtToken == null) {
            window.location.href = "/ui/login";
        } else {
            var decodedToken = jwt_decode(jwtToken);
        }

        if (decodedToken.Roles.authority !== 'ROLE_ADMIN') {
            logOut();
        }
    })
    }

function createAuthorizationTokenHeader() {
    var token = getJwtToken();
    if (token) {
        return {"Authorization": "Bearer " + token};
    } else {
        return {};
    }
}

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
};

function refreshToken() {
    var currentToken = getJwtToken();
    if (currentToken) {
        var expDate = (parseJwt(currentToken).exp);
        if ((expDate * 1000 - new Date().getTime()) < 1800000) {
            $.ajax({
                type: "GET",
                headers: {"Authorization": "Bearer " + getJwtToken()},
                url: host + 'refresh',
                statusCode: {
                    403: function () {
                        removeJwtToken();
                        window.location.href = '/ui/login'
                    },
                    401: function () {
                        removeJwtToken();
                        window.location.href = '/ui/login'
                    }
                },
                success: function (data) {
                    console.log("Old token: " + getJwtToken())
                    removeJwtToken();
                    setJwtToken(data.data.token);
                    console.log("New token: " + getJwtToken())
                },
                dataType: "json",
                contentType: "application/json"
            })
        }
    }
}

function setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}
function removeJwtToken() {
    localStorage.removeItem(TOKEN_KEY);
}
function logOut() {
    removeJwtToken();
    window.location.href = '/ui/login';
}