 $('.password-change').on("input", function() {
              var password_1 = $('#newPassword_1');
              var password_2 = $('#newPassword_2');
              var update_pasword=$('#update_pasword');
              
              if(password_1.val() == password_2.val()){
                  password_1.removeClass('input-password-not-valid');
                  password_2.removeClass('input-password-not-valid');
                  update_pasword.removeClass('disabled');
                  password_1.addClass('input-password-valid');
                  password_2.addClass('input-password-valid');
                  $('#newPassword_1_valide').addClass('input-validation');
                  $('#newPassword_2_valide').addClass('input-validation');
              }else{
                  password_1.removeClass('input-password-valid');
                  password_2.removeClass('input-password-valid');
                  update_pasword.addClass('disabled');
                  $('#newPassword_1_valide').removeClass('input-validation');
                  $('#newPassword_2_valide').removeClass('input-validation');
                  
                  password_1.addClass('input-password-not-valid');
                  password_2.addClass('input-password-not-valid');
              }
        });