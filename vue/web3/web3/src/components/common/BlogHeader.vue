<template>
  <div>
    <div class="header h-top">
      <div class="d-flex">
          <div class="header-logo">
            <a href="javascript:" @click="openAdminHome()"><img src="../../assets/tabler.svg" class="header-brand-img"></a>
          </div>
          <div class="header-user">
            <div class="link-home">
              <a href="#" class="a-btn">Linshuer' blog</a>
            </div>
            <div class="dropd-msg">
              <a href="#" class="a-bell-dropdown">
              <el-dropdown trigger="click" >
                <span class="el-dropdown-link">
                  <el-badge is-dot class="item"><i class="el-icon-arrow-down el-icon-bell"></i></el-badge>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-plus"><a href="#">黄金糕</a></el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus">狮子头</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus-outline">螺蛳粉</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-check">双皮奶</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-check">蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              </a>
            </div>
            <div class="dropd-user">
              <a href="#" class="a-user-dropdown">
              <el-dropdown trigger="click" placement="bottom-end" >
                <span class="el-dropdown-link">
                  <img class="avater-1" src="../../assets/16.jpg">
                  <span class="uu">
                    <span class="text-default" v-show="isLogin()">{{this.$store.state.currentUser}}</span>
                    <small class="d-block">Administrator</small>
                  </span>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item ><a href="javascript:" @click="openUserInfo()"><svg-icon icon-class="user" class="card-h-icon"/>个人信息</a></el-dropdown-item>
                  <el-dropdown-item ><svg-icon icon-class="mail" class="card-h-icon"/>收件箱</el-dropdown-item>
                  <el-dropdown-item ><svg-icon icon-class="settings" class="card-h-icon"/>设置</el-dropdown-item>
                  <el-dropdown-item divided><svg-icon icon-class="help-circle" class="card-h-icon" />联系</el-dropdown-item>
                  <el-dropdown-item > <svg-icon icon-class="log-out" class="card-h-icon"/>登出</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              </a>
            </div>
          </div>
      </div>
    </div>
    <div class="h-nav">
      <div class="div-menu">
        <div class="menu-left">
          <el-menu :default-active="activeIndex" class="el-menu-admin" mode="horizontal" @select="handleSelect" active-text-color="#409EFF">
          <el-menu-item class="m-item-first" index="1"><a href="javascript:" @click="openAdminHome()"><svg-icon icon-class="home" class="card-h-icon"/>首页</a></el-menu-item>
          <el-menu-item index="2"><a :underline="false" href="javascript:" @click="openPostEdit()"><svg-icon icon-class="edit" class="card-h-icon"/>发布</a></el-menu-item>
          <el-menu-item index="3"><el-link :underline="false" href="javascript:"><svg-icon icon-class="file-text" class="card-h-icon"/>文章</el-link></el-menu-item>
          <el-menu-item index="4"><el-link :underline="false" href="javascript:"><svg-icon icon-class="message-square" class="card-h-icon"/>评论</el-link></el-menu-item>
          <el-menu-item index="5"><el-link :underline="false" href="javascript:"><svg-icon icon-class="paperclip" class="card-h-icon"/>标签</el-link></el-menu-item>
          <el-menu-item index="6"><el-link :underline="false" href="javascript:"><svg-icon icon-class="mail" class="card-h-icon"/>邮件</el-link></el-menu-item>
        </el-menu>
        </div>
        <div class="menu-right">
          <el-input placeholder="请输入内容" prefix-icon="el-icon-search" class="inp-search"></el-input> 
        </div>
      </div> 
    </div>
  </div>
</template>
<style rel="stylesheet/css" lang="css">

</style>
<script>
export default {
  name: 'BlogHeader',
  data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
      };
  },
  computed:{

  },
  methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      openUserInfo(){
        this.$router.push({path: '/userinfo'});
      },
      openAdminHome(){
        this.$router.push({path: '/admin'});
      },
      openPostEdit(){
        this.$router.push({path: '/edit'});
      },
      isLogin(){
        if(sessionStorage.getItem("userName")){
          this.$store.commit("userStatus",sessionStorage.getItem("userName"));
        }else{
          this.$store.commit("userStatus",null);
          this.$message({
              message: "登录信息过期，请重新登录",
              type: 'error'
              });
          this.$router.push({path: '/login'})
        }
        return this.$store.getters.isLogin;
      }
    },
      created() {
    }
}
</script>