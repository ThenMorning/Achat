/**
* Created with Winward.
* Date: 2018/1/11
* Time: 下午3:47
*
*/
<template>
  <el-container style="position: relative;">
    <el-header style="position: absolute;z-index:20000;display: grid;align-items: center;width: 100%;height: 5%;background: teal;">
      <el-row>
        <el-col :span="3" @click.native="goBack">
          <i class="el-icon-back" style="color: white;" ></i>
        </el-col>
      </el-row>
    </el-header>
    <el-main style="position: absolute;width: 100%;padding: 20%;text-align: center;">
      <el-row>
        <el-col>
          <h1 style="font-size: 3rem;">Hi!</h1>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-input
            placeholder="请输入用户名"
            v-model="name"
            :maxlength="8"
            prefix-icon="el-icon-document"
            autofocus
            clearable>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col>
          <el-input
            type="password"
            placeholder="请输入密码"
            v-model="password"
            prefix-icon="el-icon-edit"
            autofocus
            clearable>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col>
          <el-input
            type="password"
            placeholder="请确认密码"
            v-model="confirmPassword"
            prefix-icon="el-icon-edit"
            autofocus
            clearable>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col>
          <el-button type="primary" @click.native="register" round>注册</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>

</template>
<script>
  import {postRequest} from 'src/services/util.js';

  export default {
    data() {
      return {
        name:'',
        password:'',
        confirmPassword:''
      }
    },
    watch: {},
    methods: {
      register:function () {
        if(this.name !='' && this.password != '' && this.confirmPassword != ''){
          if(this.password == this.confirmPassword){
            if(this.password.length >= 6){
              postRequest({
                url: 'http://127.0.0.1:8080/SSM/register',
                para: {
                  'username':this.name,
                  'password':this.password
                }
              })
                .then((res) => {
                  if(res.status.success){
                    this.$notify({
                      title: '提示',
                      message: '注册成功!',
                      position: 'bottom-right',
                      type:'success',
                      duration: 2000
                    });
                    this.goBack();
                  }
                  else{
                    this.$notify({
                      title: '错误',
                      message: res.status.message,
                      position: 'bottom-right',
                      duration: 2000
                    });
                  }
                })
                .catch((error) => {})
            }else{
              this.$notify({
                title: '提示',
                message: '密码最短为6位!',
                position: 'bottom-right',
                duration: 2000
              });
            }
          }
          else{
            this.$notify({
              title: '提示',
              message: '两次密码不一致!!',
              position: 'bottom-right',
              duration: 2000
            });
          }
        }else{
          this.$notify({
            title: '提示',
            message: '请填写后重试!',
            position: 'bottom-right',
            duration: 2000
          });
        }

      },
      goBack:function () {
        this.$router.push({name: 'Login'});
      }
    },
    created() {
    }
  }
</script>
<style>
</style>
