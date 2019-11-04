<template>
<div class="page">
  <div class="home-container">
    <blog-header></blog-header>
    <div class="mainWrapper">
      <div class="userinfo-container">
          <div class="user-backgroundimage">
            <el-upload
            ref="upload"
            name="background"
            class="user-bj-uploader"
            action="http://localhost:8080/admin/userinfo/background"
            :show-file-list="false"
            :on-success="handleImageSuccess"
            :before-upload="beforeImageUpload">
            <img v-if="bjimageUrl" :src="bjimageUrl" class="user-bj-image">
            <i v-else class="el-icon-plus user-bj-uploader-icon"></i>
            </el-upload>
          </div>
          <div class="user-content">
            <div class="user-content-info-avater">
                <div class="user-avaterimage">
                  <!-- <el-form :model="avatarForm" ref="avatarForm" class="avatar-form">
                    <el-form-item prop="avatar"> -->
                      <el-upload
                      ref="upload"
                      name="avatar"
                      class="user-avatar-uploader"
                      action="http://localhost:8080/admin/userinfo/avatar"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess"
                      :before-upload="beforeImageUpload">
                      <img v-if="avatarImageUrl" :src="avatarImageUrl" class="user-avatar-image">
                      <i v-else class="el-icon-plus user-avatar-uploader-icon"></i>
                      </el-upload>
                    <!-- </el-form-item>
                  </el-form> -->
                </div>
            </div>
                  <div class="user-info-edit">
                    <el-form :model="userForm" :rules="userRules"  ref="userForm"  class="user-form">
                    <el-form-item class="user-item" ><h3 class="h3-item">用户</h3><div class="info-div-userpws" v-text="userForm.username">linshuer</div></el-form-item>
                    <el-form-item class="user-item" ><h3 class="h3-item">密码</h3><div class="info-div-userpws">******</div><a href="javascript:" class="a-edit3"  @click="dialogFormVisible = true"><span><svg-icon icon-class="edit-3" class="card-icon-edit-3"/>修改</span></a></el-form-item>
                    <el-form-item class="user-item" prop="nickname">
                      <h3 class="h3-item">昵称</h3>
                      <el-input type="text" v-model="userForm.nickname" maxlength="12" show-word-limit autocomplete="off" placeholder="nickname" class="inp-user"></el-input>
                    </el-form-item>
                    <el-form-item class="user-item" prop="email">
                      <h3 class="h3-item">邮箱</h3>
                      <el-input type="text" v-model="userForm.email" autocomplete="off" placeholder="email" class="inp-user"></el-input>
                    </el-form-item>
                    <el-form-item class="user-item textarea-item" prop="sign">
                      <h3 class="h3-item">签名</h3>
                      <el-input type="textarea" placeholder="设置签名" v-model="userForm.sign" maxlength="30" show-word-limit class="textarea-sign"></el-input>
                    </el-form-item>
                    <el-form-item class="user-item-btn">
                      <el-button class="btn-user-save" @click="userFormFuc('userForm')">保存个人资料</el-button>
                    </el-form-item>
                    </el-form>
                </div>
          </div>
      </div>
    </div>
    <blog-footer></blog-footer>
  </div>
  <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
  </el-dialog>
</div>
</template>
<script>
  import blogHeader from '@/components/common/BlogHeader.vue'
  import blogFooter from '@/components/common/BlogFooter.vue'
  export default {
    name: 'userinfo',
    components: { blogHeader, blogFooter },
    data() {
      var validateNickName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入昵称'));
        } else {
          callback();
        }
      };
      return {
        bjimageUrl: 'https://pic4.zhimg.com/80/v2-2989bd9b747f067e77de86b37f20b729_r.jpg',
        avatarImageUrl: require('../assets/defaultAvatar.png'),
        dialogFormVisible:false,
        userForm: {
          username:'',
          nickname: '',
          email: '',
          sign:'太懒了，不留签名！'
        },
        userRules: {
          nickname: [
            { validator: validateNickName, trigger: 'blur' }
          ],
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
         ]
        }
      };
    },
    methods: {
      handleImageSuccess(res, file) {
        this.bjimageUrl = URL.createObjectURL(file.raw);
          if (file.response.code === 200) {
           this.bjimageUrl = file.response.data;
           this.saveBackground(file.response.data);
           console.log(file.response.data);
         }
      },
      handleAvatarSuccess(res, file) {
        this.avatarImageUrl = URL.createObjectURL(file.raw);
         if (file.response.code === 200) {
           this.avatarImageUrl = file.response.data;
           this.saveAvatar(file.response.data);
           console.log(file.response.data);
         }
      },
      beforeImageUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isLt2M = file.size / 1024 / 1024 < 1;

        if (!isJPG && !isGIF && !isPNG && !isBMP) {
             this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
        }
        if (!isLt2M) {
            this.$message.error('上传图片大小不能超过 1MB!');
        }
        return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
        },
      userFormFuc(userForm) {
        this.$refs[userForm].validate((valid) => {
          if (valid) {
            this.$axios.post('/admin/userinfo/update', {
               username: sessionStorage.getItem("userName"),
               nickname: this.userForm.nickname,
               email: this.userForm.email,
               sign: this.userForm.sign
              })
              .then(successResponse => {
                // this.responseResult = JSON.stringify(successResponse.data)
                if (successResponse.data.code === 200) {
                  this.$message({
                            message: '保存成功',
                            type: 'success'
                          });                 
                }else{
                  this.$message({
                            message: '修改失败1，请检查信息是否正确！',
                            type: 'error'
                          });                  
                  console.log(successResponse.data.data.status);
                }
              })
              .catch(failResponse => {
                  this.$message({
                            message: '修改失败2，请检查信息是否正确！',
                            type: 'error'
                          });   
              })
            }else{
              return false;
            }
        });
      },
      showUserInfo(){//刷新 页面显示信息
        this.$axios.post('/admin/userinfo/findUserInfo',{
          username: sessionStorage.getItem("userName")
        })
        .then(successResponse => {
          if(successResponse.data.code === 200){
            this.userForm.username = successResponse.data.data.user.username;
            this.userForm.nickname = successResponse.data.data.user.nickname;
            this.userForm.email = successResponse.data.data.user.email;
            this.userForm.sign = successResponse.data.data.user.sign;
            this.avatarImageUrl = successResponse.data.data.user.avatar;
            this.bjimageUrl = successResponse.data.data.user.background;
            console.log(successResponse.data.data);
          }else{
            this.$message({
              message: '页面更新失败失败',
              type: 'error'
            });
            console.log(successResponse.data.data.status);
          }
        })
        .catch(failResponse => {
          this.$message({
            message: '页面更新失败失败2',
            type: 'error'
          });
          console.log(successResponse.data.data.status);
        })
      },
      saveBackground(background){
        this.$axios.post('/admin/userinfo/saveBackground',{
          username: sessionStorage.getItem("userName"),
          background: background
        })
        .then(successResponse =>{
          if(successResponse.data.code === 200){
            this.$message({
              message: '修改背景成功',
              type: 'success'
            });
          }
        })
        .catch(failResponse => {
          this.$message({
            message: '修改背景失败',
            type: 'error'
          });
          console.log(successResponse.data.data.status);
        })
      },
      saveAvatar(avatar){
        this.$axios.post('/admin/userinfo/saveAvatar',{
          username: sessionStorage.getItem("userName"),
          avatar: avatar
        })
        .then(successResponse =>{
          if(successResponse.data.code === 200){
            this.$message({
              message: '修改头像成功',
              type: 'success'
            });
          }
        })
        .catch(failResponse => {
          this.$message({
            message: '修改头像失败',
            type: 'error'
          });
          console.log(failResponse.data.data.status);
        })
      }
    },
    created(){
      this.showUserInfo();
    },
  }
</script>

<style rel="stylesheet/css" lang="css">
@import '../styles/css/blogheader.css';
@import '../styles/css/blogfooter.css';

.userinfo-container{
    width: 1100px;
    margin: 0 auto;
    margin-top: 10px;
    background-color: #fff;
    padding-bottom: 20px;
}
  .user-bj-uploader .el-upload {
    border: 0;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    border-radius: 4px 4px 0px 0px;
  }

  .user-bj-uploader-icon ,.user-avatar-uploader-icon{
    font-size: 28px;
    color: #8c939d;
    width: 160px;
    height: 160px;
    line-height: 178px;
    text-align: center;
  }
  .user-bj-image,.user-avatar-image {
    width: 100%;
    height: 100%;
    display: block;  
    background-color: #fff;
  }
  .user-bj-image{
    width: 1100px;
    height: 264px;
  }
  .user-avatar-image {
    width: 160px;
    height: 160px;
    border-radius: 8px;
    border: 4px solid #fff;
  }
  .user-cot-cover{
      margin: 10px;
      width: 160px;
      height: 160px;
  }
  /* 内容 */
  .user-content{
    position: relative;
    margin: 0 20px 24px;
    background-color: #fff;
  }

  /* 编辑区 */
  .user-info-edit{
    padding-top: 16px;
    padding-left: 22px;
    border-left: 164px solid transparent;
  }
  .user-content-info-avater{
    position: absolute;
    top: -50px;
    left: 0;
    z-index: 1;
    border-radius: 8px;
  }

  .textarea-sign .el-textarea__inner {
    width: 618px;
    height: 65px;
    font-family: inherit;
    resize: none;
    border: none;
}
.textarea-sign .el-textarea__inner:focus{
   border: 1px solid #ebebeb;

}
  .textarea-sign {
    width: 618px;
    height: 65px;
  }
  .user-item  {
    width: 680px;
    margin-bottom: 0;
    border-bottom: 1px solid #ebebeb;
}
 .user-item .el-input__inner{
   border: none;
 }
  .user-item .el-input__inner:focus{
   border: 1px solid #ebebeb;
 }

 .h3-item{
   display: inline-block;
   padding: 2px 10px;
 }
 .inp-user{
   width: 50%;
 }

 .textarea-item{
   padding: 10px 0px;
 }
 .info-div-userpws{
   display: inline-block;
   margin-left: 20px;
   width: 275px;
 }

 .btn-user-save{
    padding: 0 16px;
    font-size: 14px;
    line-height: 32px;
    color: #0084ff;
    border-color: #0084ff;
 }

 .user-item-btn{
   margin-top: 40px;
   margin-left: 280px;
 }
 .card-icon-edit-3{
   width: 15px;
   height: 15px;

 }
 .a-edit3{
  color:#0084ff;
 }
 .a-edit3:hover{
   color:inherit;
 }
</style>