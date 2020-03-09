<style>


body {
  position: relative;
  font-family: tahoma, sans-serif;
  background: #fff;
  color: #000;
  font-size: 18px;
}

.grid {
  list-style: none;
  margin-left: -50px;
}

.gc {
  box-sizing: border-box;
  display: inline-block;
  margin-right: -.25em;
  min-height: 1px;
  padding-left: 40px;
  vertical-align: top;
}

.gc--1-of-3 {
  width: 45%;
}

.gc--2-of-3 {
  width: 55%;
}

.naccs {
  position: relative;
  max-width: 900px;
  margin: 100px auto 0;
}

.naccs .menu div {
  padding: 15px 20px 15px 40px;
  height:90px;
  margin-bottom: 10px;
  color: #212529;
  background: #fff;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  position: relative;
  vertical-align: middle;
  font-weight: 700;
  -webkit-transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
  transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
}

.naccs .menu div:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.naccs .menu div span.light {
  height: 10px;
  width: 10px;
  position: absolute;
  top: 24px;
  left: 15px;
  background-color: #fff;
  border-radius: 100%;
  -webkit-transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
  transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
}

.naccs .menu div.active span.light {
  background-color: #276999;
  left: 0;
  height: 100%;
  width: 3px;
  top: 0;
  border-radius: 0;
}

.naccs .menu div.active {
  color: #276999;
  padding: 15px 20px 15px 20px;
}

ul.nacc {
  position: relative;
  height: 0px;
  list-style: none;
  margin: 0;
  padding: 0;
  -webkit-transition: 0.5s all cubic-bezier(0.075, 0.82, 0.165, 1);
  transition: 0.5s all cubic-bezier(0.075, 0.82, 0.165, 1);
}

ul.nacc li {
  opacity: 0;
  -webkit-transform: translateX(50px);
          transform: translateX(50px);
  position: absolute;
  list-style: none;
  -webkit-transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
  transition: 1s all cubic-bezier(0.075, 0.82, 0.165, 1);
}

ul.nacc li.active {
  -webkit-transition-delay: .3s;
          transition-delay: .3s;
  z-index: 2;
  opacity: 1;
  -webkit-transform: translateX(0px);
          transform: translateX(0px);
}

ul.nacc li p {
  margin: 0;
}

.content {
  background-color: #fff;
}

/*Chat Bubbles*/
.chatlogs {
  padding:10px;
  width:100%;
  height:450px;
  background: #fbfbfb;
  overflow-x: hidden;
  overflow-y: scroll;
}

.chat {
  display:flex;
  flex-flow: row wrap;
  align-items: flex-start;
  margin-bottom:10px;
}

.chat .user-photo {
  width:45px;
  height:45px;
  background:#ccc;
  border-radius: 50%;
  overflow:hidden;
}

.chat .user-photo img {
  width:100%;
}

.chat .chat-message {
  width:80%;
  padding:15px;
  margin:5px 10px 0;
  border-radius: 10px;
  color: #fff;
  font-size: 15px;
}

.to-me .chat-message {
  background: #276999;
}

.from-me .chat-message {
  background: #ccc;
  color:#000;
}

.chat-form {
  margin-top: 20px;
  display: flex;
  align-items:flex-start;
}

.chat-form textarea{
  width:75%;
  height:50px;
  background: #fbfbfb;
  border: 2px solid #eee;
  border-radius: 3px;
  resize:none;
  padding:10px;
  font-size:13px;
  color:#333;
}

.chat-form textarea:focus {
  background: #fff;
}

.chat-form button {
  color:#ccc;
  padding:5px 15px;
  font-size:14px;
  color:#000;
  border:none;
}
	
</style>

