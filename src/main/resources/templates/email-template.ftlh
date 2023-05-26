<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
            
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="left" border="0" cellpadding="0" cellspacing="0" width="300" height="450" style="border-collapse: collapse;">
        <tr>
            <td align="center" background="https://i.ibb.co/sj3955N/OTP-bg.jpg"  bgcolor="#000000"  >
                <p style="color:#FF0000">Dear ${name}<br></br>Here is your OTP:<b>${otp}</b><br>Thanks and keep shopping with Mahavir Electronics.</p>
            </td>
        </tr>
    </table>

</body>
</html>