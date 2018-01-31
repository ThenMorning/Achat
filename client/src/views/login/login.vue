/**
* Created with Winward.
* Date: 2018/1/5
* Time: 下午4:38
*
*/
<template>
  <el-container>
    <el-main style="height: 100%;width: 100%;position: absolute;">
      <el-carousel :interval="3000"
                   type="card"
                   style="height:auto;position: relative;">
        <el-carousel-item v-for="swiperImg in swiperImgList">
         <img :src="swiperImg.src" style="width: 100%;height:100%;"/>
        </el-carousel-item>
      </el-carousel>
      <el-row style="margin-top: 5%;">
        <el-col :span="12" :offset="6">
          <el-input
            placeholder="请输入用户名"
            v-model="name"
            :maxlength ="8"
            prefix-icon="el-icon-document"
            autofocus
            clearable>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col :span="12" :offset="6">
          <el-input
            placeholder="请输入密码"
            v-model="password"
            :maxlength ="8"
            prefix-icon="el-icon-edit"
            autofocus
            clearable>
          </el-input>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col :span="12" :offset="6" style="text-align: center;">
          <el-button type="primary" @click.native="goChat" round>登录</el-button>
        </el-col>
      </el-row>
      <el-row style="margin-top: 5%;">
        <el-col :span="12" :offset="6" style="text-align: center;">
          <el-button type="text" @click.native="goRegister">注册</el-button>
        </el-col>
      </el-row>
      <i class="el-icon-question"
         style="position: absolute;bottom: 0;right: 0;font-size: 3rem;color: aqua;"
         @click="moreQA">
      </i>
    </el-main>
  </el-container>
</template>
<script>
  import {haveDirtyWords,haveSpecialChar,getRequest,postRequest} from 'src/services/util.js';
  import swiper1 from 'src/assets/img/swiper-01.png';
  import swiper2 from 'src/assets/img/swiper-02.png';
  import swiper3 from 'src/assets/img/swiper-03.png';
    export default {
        data() {
            return {
              name:'',
              password:'',
              swiperImgList:
                [
                  {src:swiper1},
                  {src:swiper2},
                  {src:swiper3},
                ]
              }
        },
        watch: {},
        methods: {
          goChat:function(){
            if(this.name && this.name != ''){
              postRequest({
                url: 'http://127.0.0.1:8080/SSM/login',
                para: {
                  'username':this.name,
                  'password':this.password
                }
              }).then((res) => {
                if(res.status.success){
                  window.name = this.name;
                  this.$router.push({name: 'Main'});
                }
                else{
                  this.$notify({
                    title: '错误',
                    message: res.status.message,
                    position: 'bottom-right',
                    duration: 2000
                  });
                }
              }).catch((error) => {
              })
            }
            else{
              this.$notify({
                title: '提示',
                message: '请填写昵称后重试!',
                position: 'bottom-right',
                duration: 2000
              });
            }





            /*if(haveSpecialChar(this.name)){
              this.$notify({
                title: '错误',
                message: '请删除特殊字符后重试!',
                position: 'bottom-right',
                duration: 1500
              });
            }
            else if(haveDirtyWords(this.name)){
              this.$notify({
                title: '错误',
                message: '请删除敏感字符后重试!',
                position: 'bottom-right',
                duration: 2000
              });
            }
            else{
              window.name = this.name;
              this.$router.push({name: 'Main'});
            }*/
          },
          goRegister:function () {
            this.$router.push({name:'Register'});
          },
          moreQA:function () {
            this.$notify({
              title: '提示',
              message: '正在建设中，敬请期待!',
              position: 'bottom-right',
              duration: 1000
            });
          }
        },
        created() {
        }
    }
</script>
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
