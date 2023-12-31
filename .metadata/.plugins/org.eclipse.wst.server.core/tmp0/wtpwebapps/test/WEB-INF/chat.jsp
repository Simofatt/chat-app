<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="comm.octest.beans.Utilisateur" %>
<%@ page import ="java.util.List" %>
<%@ page import="comm.octest.beans.ChatRoom" %>
<%@ page import="comm.octest.db.Pseudo" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="comm.octest.beans.Message" %>
<jsp:include page="navbar.jsp" />
<%@ page import="java.security.*" %>
    
      

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        
            <% 
            String email =  (String) session.getAttribute("email");
            String withClientId = (String) request.getParameter("withClientId");
            Pseudo user = new Pseudo(email);

           
          
              ArrayList<Message> my_messages = new ArrayList<Message>();
              try {
                  my_messages = (ArrayList<Message>) user.getMessagesWithUser(withClientId);
              } catch (Exception e) {
              }
              try {
                  user.removeMsg(withClientId);
              } catch (Exception e) {

              }


          %>
           
   <script type="text/javascript">

 let withClientId = "" ;
      window.addEventListener("load", function (event) {
        let pseudo = "<%=email%>";
        let withClientId = "<%=withClientId%>";
        
        
        let ws = new WebSocket("ws://localhost:8085/test/chatroom/" + pseudo);
        let txtHistory = document.getElementById("discussion");
        let txtMessage = document.getElementById("txtMessage");
        let showInputFile = document.getElementById("showInputFile");

        txtMessage.focus();

        ws.addEventListener("open", function (evt) {
            console.log("Connection established");
        });
        
        let btnSend = document.getElementById("btnSend");
        let fileInput = document.getElementById("fileInput");

        showInputFile.addEventListener("click", function () {
            if (fileInput.style.display=="none"){
                fileInput.style.display="block";
                txtMessage.style.display="none";
            }
            else {
                fileInput.style.display="none";
                txtMessage.style.display="block";
            }
            console.log("show");
        })

        btnSend.addEventListener("click", function (clickEvent) {
    
        	 $.ajax({
                 url: "CryptMessage",
                 type: "POST",
                 data: {message: txtMessage.value},
                 
                 success: function(data) {
                	 console.log("data : " + data)
                     // Receive the treated variable from the servlet or JSP
                     dataJson = JSON.parse(data);
                	 console.log("json : " + dataJson);
                     encryptedMessage = dataJson.encryptedMessage;
                     publicKey = dataJson.publicKey;
                     signature = dataJson.signature;
                     // Do something with the treated variable
                     console.log(encryptedMessage + " Key : " + publicKey);
                     let message = {
                             to: withClientId,
                             from : pseudo,
                             message: encryptedMessage,
                             publicKey: publicKey,
                             signature : signature
                            
                         };
                   if (fileInput.files.length > 0) {
                       let file = fileInput.files[0];
                       let reader = new FileReader();
                       reader.onload = function (loadEvent) {
                           let fileData = loadEvent.target.result.split(",")[1];
                           message.file = {
                               name: file.name,
                               type: file.type,
                               data: fileData
                           };
                           ws.send(JSON.stringify(message));
                       };
                       reader.readAsDataURL(file);
                       txtHistory.innerHTML += '<div class="chat outgoing"> <div class="details"> <p>'+ 'Vous avez envoyé un fichier' + '</p></div> </div>';
                       txtMessage.value = "";
                       txtMessage.focus();
                   } else {
                       ws.send(JSON.stringify(message));
                       txtHistory.innerHTML += '<div class="chat outgoing"> <div class="details"> <p>'+ txtMessage.value + '</p></div> </div>';
                       txtMessage.value = "";
                       txtMessage.focus();
                   }
                     	
                     
                 }
            
             
             });
        	
        });
        
        ws.addEventListener("message", function (evt) {
        	 let message_infos = evt.data;
             let infos_arr = message_infos.split(",");
             if(infos_arr[0] == withClientId){
            	 let publicKey = infos_arr[2];
            	 let signature = infos_arr[3];
                 let msgData = infos_arr[4].split(":");
                 let typeMsg = msgData[0];
                 if (typeMsg == "Msg"){
                	 $.ajax({
                         url: "DecryptMessage",
                         type: "POST",
                         data: {message: msgData[1], publicKey: publicKey, signature: signature},
                         
                         success: function(data) {
                        	 console.log("data : " + data)
                        	 console.log("Receive new message: " + message_infos);
                             console.log("jkllll" + msgData[1]);
                             txtHistory.innerHTML += '<div class="chat incoming"> <div class="details"> <p>'+ data + '</p></div> </div>';
                         }
                    
                     
                     });
                 	
                    
                 }
                 else if (typeMsg == "File"){
                     console.log("Receive new message: " + msgData[1]);
                     let fileInfos = msgData[1].split("|");
                     txtHistory.innerHTML += '<div class="chat incoming"> <div class="details"> <a href="http://localhost/php/chatApp-ressources//'+fileInfos[0]+'_'+fileInfos[1] + '"> Telecharger Piéce jointe </a></div> </div>';
                 }

             }
             
        	
        	
           
        });

      

    });


</script>
         <style>

            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                text-decoration: none;
                font-family: 'Poppins', sans-serif;
            }
            body{
                background: url("BackgroundServlet");
            }
            #fileInput{
                display: none;
            }
            .container{
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                padding: 0 10px;
            }
            .wrapper{
                background: #fff;
                max-width: 450px;
                width: 100%;
                border-radius: 16px;
                box-shadow: 0 0 128px 0 rgba(0,0,0,0.1),
                0 32px 64px -48px rgba(0,0,0,0.5);
                position : relative ; 
                left : 500px;
            }

            /* Login & Signup Form CSS Start */
            .form{
                padding: 25px 30px;
            }
            .form header{
                font-size: 25px;
                font-weight: 600;
                padding-bottom: 10px;
                border-bottom: 1px solid #e6e6e6;
            }
            .form form{
                margin: 20px 0;
            }
            .form form .error-text{
                color: #721c24;
                padding: 8px 10px;
                text-align: center;
                border-radius: 5px;
                background: #f8d7da;
                border: 1px solid #f5c6cb;
                margin-bottom: 10px;
                display: none;
            }
            .form form .name-details{
                display: flex;
            }
            .form .name-details .field:first-child{
                margin-right: 10px;
            }
            .form .name-details .field:last-child{
                margin-left: 10px;
            }
            .form form .field{
                display: flex;
                margin-bottom: 10px;
                flex-direction: column;
                position: relative;
            }
            .form form .field label{
                margin-bottom: 2px;
            }
            .form form .input input{
                height: 40px;
                width: 100%;
                font-size: 16px;
                padding: 0 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .form form .field input{
                outline: none;
            }
            .form form .image input{
                font-size: 17px;
            }
            .form form .button input{
                height: 45px;
                border: none;
                color: #fff;
                font-size: 17px;
                background: #333;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 13px;
            }
            .form form .field i{
                position: absolute;
                right: 15px;
                top: 70%;
                color: #ccc;
                cursor: pointer;
                transform: translateY(-50%);
            }
            .form form .field i.active::before{
                color: #333;
                content: "\f070";
            }
            .form .link{
                text-align: center;
                margin: 10px 0;
                font-size: 17px;
            }
            .form .link a{
                color: #333;
            }
            .form .link a:hover{
                text-decoration: underline;
            }


            /* Users List CSS Start */
            .users{
                padding: 25px 30px;
            }
            .users header,
            .users-list a{
                display: flex;
                align-items: center;
                padding-bottom: 20px;
                border-bottom: 1px solid #e6e6e6;
                justify-content: space-between;
            }
            .wrapper img{
                object-fit: cover;
                border-radius: 50%;
            }
            .users header img{
                height: 50px;
                width: 50px;
            }
            :is(.users, .users-list) .content{
                display: flex;
                align-items: center;
            }
            :is(.users, .users-list) .content .details{
                color: #000;
                margin-left: 20px;
            }
            :is(.users, .users-list) .details span{
                font-size: 18px;
                font-weight: 500;
            }
            .users header .logout{
                display: block;
                background: #333;
                color: #fff;
                outline: none;
                border: none;
                padding: 7px 15px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 17px;
            }
            .users .search{
                margin: 20px 0;
                display: flex;
                position: relative;
                align-items: center;
                justify-content: space-between;
            }
            .users .search .text{
                font-size: 18px;
            }
            .users .search input{
                position: absolute;
                height: 42px;
                width: calc(100% - 50px);
                font-size: 16px;
                padding: 0 13px;
                border: 1px solid #e6e6e6;
                outline: none;
                border-radius: 5px 0 0 5px;
                opacity: 0;
                pointer-events: none;
                transition: all 0.2s ease;
            }
            .users .search input.show{
                opacity: 1;
                pointer-events: auto;
            }
            .users .search button{
                position: relative;
                z-index: 1;
                width: 47px;
                height: 42px;
                font-size: 17px;
                cursor: pointer;
                border: none;
                background: #fff;
                color: #333;
                outline: none;
                border-radius: 0 5px 5px 0;
                transition: all 0.2s ease;
            }
            .users .search button.active{
                background: #333;
                color: #fff;
            }
            .search button.active i::before{
                content: '\f00d';
            }
            .users-list{
                max-height: 350px;
                overflow-y: auto;
            }
            :is(.users-list, .chat-box)::-webkit-scrollbar{
                width: 0px;
            }
            .users-list a{
                padding-bottom: 10px;
                margin-bottom: 15px;
                padding-right: 15px;
                border-bottom-color: #f1f1f1;
            }
            .users-list a:last-child{
                margin-bottom: 0px;
                border-bottom: none;
            }
            .users-list a img{
                height: 40px;
                width: 40px;
            }
            .users-list a .details p{
                color: #67676a;
            }
            .users-list a .status-dot{
                font-size: 12px;
                color: #468669;
                padding-left: 10px;
            }
            .users-list a .status-dot.offline{
                color: #ccc;
            }

            /* Chat Area CSS Start */
            .chat-area header{
                display: flex;
                align-items: center;
                padding: 18px 30px;
            }
            .chat-area header .back-icon{
                color: #333;
                font-size: 18px;
            }
            .chat-area header img{
                height: 45px;
                width: 45px;
                margin: 0 15px;
            }
            .chat-area header .details span{
                font-size: 17px;
                font-weight: 500;
            }
            .chat-box{
                position: relative;
                min-height: 500px;
                max-height: 500px;
                overflow-y: auto;
                padding: 10px 30px 20px 30px;
                background: #f7f7f7;
                box-shadow: inset 0 32px 32px -32px rgb(0 0 0 / 5%),
                inset 0 -32px 32px -32px rgb(0 0 0 / 5%);
            }
            .chat-box .text{
                position: absolute;
                top: 45%;
                left: 50%;
                width: calc(100% - 50px);
                text-align: center;
                transform: translate(-50%, -50%);
            }
            .chat-box .chat{
                margin: 15px 0;
            }
            .chat-box .chat p{
                word-wrap: break-word;
                padding: 8px 16px;
                box-shadow: 0 0 32px rgb(0 0 0 / 8%),
                0rem 16px 16px -16px rgb(0 0 0 / 10%);
            }
            .chat-box .outgoing{
                display: flex;
            }
            .chat-box .outgoing .details{
                margin-left: auto;
                max-width: calc(100% - 130px);
            }
            .outgoing .details p{
                background: #333;
                color: #fff;
                border-radius: 18px 18px 0 18px;
            }
            .chat-box .incoming{
                display: flex;
                align-items: flex-end;
            }
            .chat-box .incoming img{
                height: 35px;
                width: 35px;
            }
            .chat-box .incoming .details{
                margin-right: auto;
                margin-left: 10px;
                max-width: calc(100% - 130px);
            }
            .incoming .details p{
                background: #fff;
                color: #333;
                border-radius: 18px 18px 18px 0;
            }
            .typing-area{
                padding: 18px 30px;
                display: flex;
                justify-content: space-between;
            }
            .typing-area input{
                height: 45px;
                width: calc(100% - 58px);
                font-size: 16px;
                padding: 0 13px;
                border: 1px solid #e6e6e6;
                outline: none;
                border-radius: 5px 0 0 5px;
            }
            .typing-area button{
                color: #fff;
                width: 55px;
                border: none;
                outline: none;
                background: #333;
                font-size: 19px;
                cursor: pointer;
                opacity: 0.7;
                border-radius: 0 5px 5px 0;
                transition: all 0.3s ease;
            }
            .typing-area button.active{
                opacity: 1;
                pointer-events: auto;
            }

            /* Responive media query */
            @media screen and (max-width: 450px) {
                .form, .users{
                    padding: 20px;
                }
                .form header{
                    text-align: center;
                }
                .form form .name-details{
                    flex-direction: column;
                }
                .form .name-details .field:first-child{
                    margin-right: 0px;
                }
                .form .name-details .field:last-child{
                    margin-left: 0px;
                }

                .users header img{
                    height: 45px;
                    width: 45px;
                }
                .users header .logout{
                    padding: 6px 10px;
                    font-size: 16px;
                }
                :is(.users, .users-list) .content .details{
                    margin-left: 15px;
                }

                .users-list a{
                    padding-right: 10px;
                }

                .chat-area header{
                    padding: 15px 20px;
                }
                .chat-box{
                    min-height: 400px;
                    padding: 10px 15px 15px 20px;
                }
                .chat-box .chat p{
                    font-size: 15px;
                }
                .chat-box .outogoing .details{
                    max-width: 230px;
                }
                .chat-box .incoming .details{
                    max-width: 265px;
                }
                .incoming .details img{
                    height: 30px;
                    width: 30px;
                }
                .chat-area form{
                    padding: 20px;
                }
                .chat-area form input{
                    height: 40px;
                    width: calc(100% - 48px);
                }
                .chat-area form button{
                    width: 45px;
                }
            }</style>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

    </head>
    <body>

    <div class="wrapper">
        <section class="chat-area">
            <header>
                <a href="ContactsServlet" class="back-icon"><i class="fas fa-arrow-left"></i></a>
                <img src="ImageProfileServlet" alt="">
                <div class="details">
                    <span><%=withClientId%></span>
                    <p>status</p>
                </div>
            </header>
            <div id="discussion" class="chat-box">
  <% for (Message msg: my_messages) {
                        String position = "";
                        if (msg.from.equals(email)){
                            position = "outgoing";
                        }
                        else {
                            position = "incoming";
                        }
                        if (msg.type.equals("text")){ %>
                    <div class="chat <%=position%>"> <div class="details"> <p> <%=msg.msg%> </p></div> </div>
                    <% }
                    else { %>
                    <div class="chat <%=position%>"> <div class="details"> <a href="http://localhost/php/chatApp-ressources/<%=msg.msg%>"> Telecharger Piéce jointe </a></div> </div>
                    <% }
                    } %>
            </div>
              <div class="typing-area">
                    <input type="text" class="incoming_id" name="incoming_id" value="<?php echo $user_id; ?>" hidden>
                    <input type="text" name="message" id="txtMessage" class="input-field" placeholder="Type a message here..." autocomplete="off">
                    <input type="file" id="fileInput">
                    <button id="showInputFile">File</button>
                    <button id="btnSend"><i class="fab fa-telegram-plane"></i></button>
                </div>
        </section>
    </div>

    </body>
   
</html>
