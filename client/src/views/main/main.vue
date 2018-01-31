/**
* Created with Winward.
* Date: 2018/1/5
* Time: 下午6:11
*
*/
<template>
  <el-container style="height: 100%;width: 100%;">
    <el-row style="height: 100%;width: 100%;position: absolute;overflow: hidden;">
      <el-col ref="leftSider" :span="openLeft?6:0" style="height:100%;">
        <el-aside width="100%"
                  style="width:100%;height:100%;position: relative;">
          <el-menu
            class="siderMenu semitransparentGrind"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1">
              <i class="el-icon-menu"></i>
              <span slot="title">Achat</span>
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-view"></i>
                <span>主题</span>
              </template>
              <el-menu-item-group>
                <template slot="title">点击按钮切换</template>
                <el-menu-item index="2-1" style="color:teal;min-width: 100%;" @click.native="toggleTheme(0)">默认
                </el-menu-item>
                <el-menu-item index="2-2" style="color:blue;min-width: 100%;" @click.native="toggleTheme(1)">蓝色
                </el-menu-item>
                <el-menu-item index="2-3" style="color:red;min-width: 100%;" @click.native="toggleTheme(2)">红色
                </el-menu-item>
                <el-menu-item index="2-4" style="color:black;min-width:100%;" @click.native="toggleTheme(3)">黑色
                </el-menu-item>
                <el-menu-item index="2-5" style="color:lightgreen;min-width:100%;" @click.native="toggleTheme(4)">原谅绿
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-warning"></i>
                <span>更名</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="3-1"
                              style="color:red;min-width: 100%;"
                              @click.native="logOut">退出
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
      </el-col>
      <el-col ref="main" :span="(openLeft||openRight)?18:24" style="height:100%;">
        <el-container style="height: 100%;width: 100%;position: relative">
          <el-header height="5%" :class="currentTheme+'-header'">
            <i class="el-icon-menu"
               style="position: absolute;top: 1.5%;left: 1.5%;"
               @click="toggleLeftSider"
            ></i>
            <i class="el-icon-more"
               style="position: absolute;top: 1.5%;right: 1.5%;"
               @click="toggleRightSider"
            ></i>
          </el-header>
          <el-main id="main" ref="main" height="90%"
                   style="overflow: auto;overflow-x: hidden;-webkit-overflow-scrolling:touch;">
            <el-row v-for="message in messageList" style="width: 100%;margin-top: 1%;">
              <!--别人的发言-->
              <el-col v-if="message.type == 'chat' && message.name != name">
                <div role="tooltip"
                     id="el-popover-5007"
                     aria-hidden="false"
                     class="el-popover el-popper el-popover--plain"
                     :class="{'aboutMe':message.content.indexOf('@'+name+'@') != -1}"
                     style="position: relative!important;width:auto;float:left;"
                     x-placement="right">
                  <i class="el-icon-bell"
                     style="position: absolute;top: 0;right: 0;font-size: 1.5rem;"
                     @click="atAction(message.name)"></i>
                  <div class="el-popover__title" style="font-family: fantasy;">
                    {{message.name}}:
                  </div>
                  <label style="word-break: break-all; margin-left: 10%;">
                    {{message.content}}
                  </label>
                  <div x-arrow="" class="popper__arrow" style="top: 15.5px;"></div>
                </div>
              </el-col>
              <!--自己的发言-->
              <el-col v-if="message.type == 'chat' && message.name == name">
                <div role="tooltip"
                     id="el-popover-1568"
                     aria-hidden="false"
                     class="el-popover el-popper el-popover--plain"
                     style="position: relative!important;width:auto;float:right;background: lightgreen;"
                     x-placement="left">
                  <div class="el-popover__title" style="font-family: fantasy;">
                    {{message.name}}:
                  </div>
                  <label style="word-break: break-all; margin-left: 10%;">
                    {{message.content}}
                  </label>
                  <div x-arrow="" class="popper__arrow" style="top: 15.5px;"></div>
                </div>
              </el-col>
              <!--上线提醒-->
              <el-col v-if="message.type == 'goOnline'" style="text-align: center;">
                <span style="white-space: nowrap;">{{message.name}}上线了</span>
              </el-col>
              <!--下线提醒-->
              <el-col :span="10" :offset="7" v-if="message.type == 'goOutline'" style="text-align: center;">
                <span style="white-space: nowrap;">{{message.name}}下线了</span>
              </el-col>
            </el-row>
          </el-main>
          <el-footer ref="footer"
                     :class="currentTheme+'-footer'"
                     style="position: relative;margin-top:10px;bottom:0;width: 100%;height:auto;">
            <el-badge :value="newMessageCount"
                      v-show="newMessageCount"
                      class="item"
                      style="position: absolute;right: 11px;top: -18px;z-index: 2000;"
                      @click.native="goToBottom">
              <i class="el-icon-info"></i>
            </el-badge>
            <el-input
              ref="toBeSentMessageInput"
              type="textarea"
              :placeholder="_placeholder"
              :readonly="_readonly"
              :maxlength="100"
              autosize
              resize="none"
              v-model="toBeSentMessage"
              @keyup.enter.native="sentMessage">
            </el-input>
            <el-container style="width:100%;">
              <el-button-group style="width:100%;display: flex;flex:1;">
                <el-button type="primary" icon="el-icon-star-off" style="flex:1;"></el-button>
                <el-button type="primary" icon="el-icon-upload2" style="flex:1;"></el-button>
                <el-button type="primary" icon="el-icon-picture" style="flex:1;"></el-button>
                <el-button type="primary" icon="el-icon-circle-plus-outline" style="flex:1;"></el-button>
                <el-button type="primary" style="flex:1;" @click.native="sentMessage">发送</el-button>
              </el-button-group>
            </el-container>
          </el-footer>
        </el-container>
      </el-col>
      <el-col ref="rightSider" :span="openRight?6:0" style="height:100%;">
        <el-aside width="100%"
                  style="width:100%;height:100%;position: relative;">
          <el-menu
            class="siderMenu semitransparentGrind"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1"
                          style="color:white;min-width: 100%;" @click.native="atAction(user)">
              <i class="el-icon-tickets"></i>
              <span slot="title">在线成员</span>
            </el-menu-item>
            <el-menu-item :index="(index+2)" v-for="(user,index) in onLineUserList"
                          style="color:white;min-width: 100%;" @click.native="atAction(user)">
              <i class="el-icon-caret-right"></i>
              <span slot="title">{{user}}</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
      </el-col>
    </el-row>
  </el-container>

</template>
<script>
  import {haveDirtyWords, deleteSpecialChar} from 'src/services/util.js';

  export default {
    data() {
      return {
        name: '',
        toBeSentMessage: '',
        messageList: [],
        _webSocket: null,
        _placeholder: '请输入内容',
        _readonly: false,
        currentTheme: 'default',
        themeList: ['default', 'blue', 'red', 'black', 'green'],
        openLeft: false,
        openRight: false,
        newMessageCount: 0,
        onLineUserList: []
      }
    },
    watch: {
      /*toBeSentMessage: function () {
        this.$nextTick(()=>{
          this.$refs.main.$el.style.height = document.documentElement.clientHeight * 0.95 -this.$refs.main.$el.offsetHeight + 'px' ;
        })
      }*/
    },
    methods: {
      sentMessage: function () {
        let self = this;
        let timer = null;
        let timerIndex = 9;
        this.toBeSentMessage = this.toBeSentMessage.replace(/[\r\n]/g, "");
        if (!this._readonly && this.toBeSentMessage && this.toBeSentMessage != '') {
          if (!haveDirtyWords(deleteSpecialChar(this.toBeSentMessage))) {
            this._webSocket.send(JSON.stringify({
              name: self.name,
              type: 'chat',
              content: this.toBeSentMessage,
              time: new Date().toLocaleString()
            }));
            this.toBeSentMessage = '';
            timer = setInterval(() => {
              this._readonly = true;
              this.toBeSentMessage = timerIndex + '秒后可以再次发言';
              timerIndex--;
              if (timerIndex <= 0) {
                this.toBeSentMessage = '';
                this._readonly = false;
                clearInterval(timer);
              }
            }, 1000)
          }
          else {
            this.$notify({
              title: '错误',
              message: '请删除敏感字符后重试!',
              position: 'bottom-right',
              duration: 2000
            });
          }
        }
      },
      toggleLeftSider: function () {
        this.openRight = false;
        this.openLeft = !this.openLeft;
      },
      toggleRightSider: function () {
        this.openLeft = false;
        this.openRight = !this.openRight;
      },
      toggleTheme: function (type) {
        this.currentTheme = this.themeList[type];
      },
      logOut: function () {
        this.$router.push({name: 'Login'});
      },
      atAction: function (name) {
        if (name == this.name) {
          return;
        }
        if (!this._readonly) {
          this.$refs.toBeSentMessageInput.focus();
          this.toBeSentMessage += "@" + name + '@ ';
        }
      },
      goToBottom: function () {
        let messageListHeight = document.getElementById('main').scrollHeight;
        this.$nextTick(() => {
          document.getElementById('main').scrollTo(0, messageListHeight);
          this.newMessageCount = 0;
        });
      },
      onLineUserPush: function (name) {
        this.onLineUserList.push(name);
      },
      onLineUserPop: function (name) {
        this.onLineUserList.forEach((_name, index, arr) => {
          if (_name == name) {
            arr.splice(index, 1);
          }
        })
      }
    },
    created() {
      this.name = window.name;
      /* 封装 WebSocket 实例化的方法  */
      let CreateWebSocket = (function () {
        return function (urlValue) {
          if (window.WebSocket) return new WebSocket(urlValue);
          if (window.MozWebSocket) return new MozWebSocket(urlValue);
          return false;
        }
      })();

      let webSocket = null;
      let self = this;
      /* 实例化 WebSocket 连接对象, 地址为 ws 协议 */
      webSocket = CreateWebSocket("ws://120.79.149.105:8200");
      /* 连接到服务端时*/
      /* type为消息类型*/
      webSocket.onopen = function () {
        webSocket.send(JSON.stringify({name: self.name, type: 'goOnline', time: new Date().toLocaleString()}));
      };

      /* 接收到服务端的消息时 */
      webSocket.onmessage = function (msg) {
        self.messageList.push(JSON.parse(msg.data));
        if (JSON.parse(msg.data).type == 'goOnline') {
          self.onLineUserPush(JSON.parse(msg.data).name);
        }
        if (JSON.parse(msg.data).type == 'goOutline') {
          self.onLineUserPop(JSON.parse(msg.data).name);
        }
        if (JSON.parse(msg.data).name != self.name) {
          self.newMessageCount++;
        } else {
          self.goToBottom();
        }
        if (JSON.parse(msg.data).content && JSON.parse(msg.data).content.indexOf("@" + name + '@') != -1) {
          self.$message({
            showClose: true,
            message: '有人@了你!'
          });
        }
      };

      /* 关闭时 */
      webSocket.onclose = function () {
        this.$notify({
          title: '提示',
          message: '服务器关闭,请稍后重试!',
          position: 'bottom-right',
          duration: 3000
        });
      };
      this._webSocket = webSocket;
    }
  }
</script>
<style lang="scss">
  @import 'src/element-variables.scss';

  .el-main {
    padding: 0;
  }

  .el-footer {
    padding: 0;
  }

  .el-menu {
    background: transparent;
    padding: 0;
    ul, li, div {
      padding: 0 !important;
    }
    li {
      text-align: center;
    }
  }

  .el-popper[x-placement^="left"] .popper__arrow::after {
    border-left-color: lightgreen;
  }

  .siderMenu {
    padding: 0;
    height: 99%;
  }

  .aboutMe {
    background: coral;
    .popper__arrow::after {
      border-right-color: coral !important;
    }
  }

  /*default theme*/
  .default-header {
    background: $defaultBgColor !important;
    color: white;
  }

  .default-footer {
    button {
      background: $defaultBgColor !important;
      border: none;
    }
  }

  /*blue theme*/
  .blue-header {
    background: $blueBgColor !important;
    color: white;
  }

  .blue-footer {
    button {
      background: $blueBgColor !important;
      border: none;
    }
  }

  /*red theme*/
  .red-header {
    background: $redBgColor !important;
    color: white;
  }

  .red-footer {
    button {
      background: $redBgColor !important;
      border: none;
    }
  }

  /*black theme*/
  .black-header {
    background: $blackBgColor !important;
    color: white;
  }

  .black-footer {
    button {
      background: $blackBgColor !important;
      border: none;
    }
  }

  /*green theme*/
  .green-header {
    background: $greenBgColor !important;
    color: white;
  }

  .green-footer {
    button {
      background: $greenBgColor !important;
      border: none;
    }
  }

  /**
    * 整体居中功能；
    * 背景透明虚化
    * 溢出隐藏
    * 边缘圆角化
    * 文字增加淡阴影
    */
  .semitransparentGrind {
    position: relative;
    margin: 0 auto;
    /*padding: 1em;*/
    //max-width: 23em;
    background: hsla(0, 0%, 100%, .25) border-box;
    overflow: hidden;
    border-radius: .3em;
    box-shadow: 0 0 0 1px hsla(0, 0%, 100%, .3) inset,
    0 .5em 1em rgba(0, 0, 0, 0.6);
    text-shadow: 0 1px 1px hsla(0, 0%, 100%, .3);
  }

  /*使用滤镜模糊边缘*/
  .semitransparentGrind::before {
    content: '';
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    margin: -30px;
    z-index: -1;
    -webkit-filter: blur(20px);
    filter: blur(20px);
  }
</style>

