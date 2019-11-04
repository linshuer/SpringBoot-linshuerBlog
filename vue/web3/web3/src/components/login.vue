<template>
<div class="login-container" >
  <!-- :style="backgroundDiv" -->
<el-form :model="ruleForm"  :rules="rules" ref="ruleForm"  class="login-form">
    <h2 class="logo-h3">Shuer Blog</h2>
  <el-form-item  prop="username">
    <span class="svg-container">
      <svg-icon icon-class="username" class="svg-icon-class"/>
    </span>
    <el-input type="text" v-model="ruleForm.username" autocomplete="off" placeholder="username" class="inp-login"></el-input>
  </el-form-item>
  <el-form-item  prop="password">
    <span class="svg-container">
      <svg-icon icon-class="password"/>
    </span>
    <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="password" show-password el-icon-ump-mima class="inp-login"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button style="width:100%" type="primary" @click="loginForm('ruleForm')">Sign in</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
  export default {
    data() {
      var validateUser = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { validator: validateUser, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ]
        }
        // backgroundDiv: {
        //   backgroundImage: "url(" + require("../assets/b1.png") + ")",
        //   backgroundRepeat:'no-repeat',
        //   backgroundSize:'100% 100%'
        // }
      };
    },
    methods: {
      loginForm(ruleForm) {
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            this.$axios.post('/admin/login', {
               username: this.ruleForm.username,
               password: this.ruleForm.password
              })
              .then(successResponse => {
                this.responseResult = JSON.stringify(successResponse.data)
                if (successResponse.data.code === 200) {
                  this.$message({
                            message: successResponse.data.data.status,
                            type: 'success'
                          });  
                  //将用户名存入sessionStorage
                  sessionStorage.setItem("userName",successResponse.data.data.userName) 
                  //将用户名存入vuex
                  this.$store.dispatch("setUser",successResponse.data.data.userName)     
                  //打印login状态
                  console.log(this.$store.state.isLogin)         
                  this.$router.push({path: '/admin'})
                }else{
                  this.$message({
                            message: successResponse.data.data.status,
                            type: 'error'
                          });                  
                  console.log(successResponse.data.data.status);
                }
              })
              .catch(failResponse => {
                console.log("失败"+this.ruleForm.username+this.ruleForm.password);
              })
            }else{
              return false;
            }
        });
      }
    }
  }
</script>
<style rel="stylesheet/css" lang="css">
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    height: 314px;
    max-width: 100%;
    padding: 35px 35px 15px;
    margin: 120px auto;
    background-color: #fff;
  }
  .login-container{
    position: fixed;
    width: 100%;
    height: 100%;
  }
  .login-container{
    background-image: url("../assets/sign_bg.zhihu.png");
    background-repeat: no-repeat;
    background-color: #b8e5f8;
    background-size: cover;
    width: 100%;
    height: 100vh;
    overflow: auto;
  }
  .inp-login>.el-input__inner {
    padding: 12px 5px 12px 15px;
    height: 47px;
    border: 0;
  }
 .el-form-item__content {
    border-bottom: 1px solid #dcdfe6;
    margin-left: 0px;
  }
.logo-h3{
    font-size: 26px;
    font-weight: 400;
    color: #0084ff;
    margin: 0 auto 40px;
    text-align: center;
    font-weight: 700;
 }
.svg-container {
    padding: 6px 5px 6px 15px;
    vertical-align: middle;
    width: 10px;
    display: inline-block;
 }

.login-container .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    font-size: 14px;
}
.svg-icon{
  width: 16px;
  height: 16px;
}
</style>
