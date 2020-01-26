declare var FB: any;
export function checkLoginState() {
    FB.getLoginStatus(function(response) {
      console.log(response, response.status, response.status == 'connected');
      if (response.status == 'connected') {
        return true;
    }
  });
  return false;
}