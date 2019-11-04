<template>
<div class="page">
  <div class="home-container">
    <blog-header></blog-header>
    <div class="mainWrapper">
      <div class="main-container">
        <div class="main-header">
          <h1 class="main-title">仪表盘</h1>
        </div>
        <div class="main-title-card">
          <el-row :gutter="24">
            <el-col :span="6">
              <el-card class="box-card card-box-bar" shadow="never">
                <span class="svg-container">
                  <svg-icon icon-class="edit" class="card-icon"/>
                </span>
                <span class="uu main-uu">
                  <span class="text-default maint-td">43</span> 
                  <small class="d-block main-d-b">文章</small>
                </span>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="box-card card-box-bar" shadow="never">
                <span class="svg-container">
                  <svg-icon icon-class="edit" class="card-icon"/>
                </span>
                <span class="uu main-uu">
                  <span class="text-default maint-td">43</span> 
                  <small class="d-block main-d-b">文章</small>
                </span>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="box-card card-box-bar" shadow="never">
                <span class="svg-container">
                  <svg-icon icon-class="edit" class="card-icon"/>
                </span>
                <span class="uu main-uu">
                  <span class="text-default maint-td">43</span> 
                  <small class="d-block main-d-b">文章</small>
                </span>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="box-card card-box-bar" shadow="never">
                <span class="svg-container">
                  <svg-icon icon-class="edit" class="card-icon"/>
                </span>
                <span class="uu main-uu">
                  <span class="text-default maint-td">43</span> 
                  <small class="d-block main-d-b">文章</small>
                </span>
              </el-card>
            </el-col>
          </el-row>
          <div class="card-left">
          <el-row :gutter="24"> 
            <el-col :span="12" >
              <el-card class="box-card card-box-content" shadow="never">
                <div slot="header" class="clearfix">
                  <span>最近文章</span>
                </div>
                <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="card-r-image">
                <div class="card-r-content">
                  <div class="clearfix">
                    <div><a>标题标题标题标题...</a></div>
                    <div><p>内容内容内容内容内容内容内容内容内容....</p></div>
                    <div>
                    <time class="time">2016-05-02</time>
                    <svg-icon icon-class="edit" class="card-r-icon"/>
                    </div>
                  </div>
                </div>
              </el-card>
              <el-card class="box-card card-box-content" shadow="never">
                <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="card-r-image">
                <div class="card-r-content">
                  <div class="clearfix">
                    <div><a>标题标题标题标题...</a></div>
                    <div><p>内容内容内容内容内容内容内容内容内容....</p></div>
                    <div>
                    <time class="time">2016-05-02</time>
                    <svg-icon icon-class="edit" class="card-r-icon"/>
                    </div>
                  </div>
                </div>
              </el-card>
            </el-col>

            <el-col :span="12" >
              <el-card class="box-card card-liuyan" shadow="never">
                <div slot="header" class="clearfix">
                  <span>最近留言</span>
                </div>
                <div class="card-biao"></div>
                <el-table
                  :data="tableData"
                  style="width: 100%">
                  <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="地址">
                  </el-table-column>
                </el-table>
              </el-card>
            </el-col>
          </el-row>
          </div>
        </div>
      </div>
    </div>
  </div>
  <blog-footer></blog-footer>
</div>
</template>
<script>
  import blogHeader from '@/components/common/BlogHeader.vue'
  import blogFooter from '@/components/common/BlogFooter.vue'
  export default {
    name: 'admin',
    components: { blogHeader, blogFooter },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路'
          }]
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    created() {
    }
  }
  // 悬浮

</script>
<style rel="stylesheet/css" lang="css">
@import '../styles/css/blogheader.css';
@import '../styles/css/blogfooter.css';
/* main部分 */
.main-header{
  display: flex;
  margin: 16px 0 14px;
}
.main-container{
  width: 1100px;
  padding: 0 12px;
  margin: 0 auto;
}
.main-title {
    margin: 0;
    font-size: 20px;
    font-weight: 400;
    line-height: 30px;
    vertical-align: inherit;
}

.card-icon{
  width: 30px;
  height: 45px;
  color: #409EFF;
}
.maint-td{
  font-size: 24px;
}
.main-uu{
  margin-left: 70px;
}
.card-box-bar{
  margin-bottom: 20px;
}
.el-card__header{
  padding-top: 18px;
  padding-bottom: 14px;
}

.card-liuyan>.el-card__body{
  padding: 0px;
}

.card-biao{
  height: 160px;
  border-bottom: 1px solid #EBEEF5;
}
.card-r-image{
  height: 140px;
  width: 200px;
}

.card-r-content{
  display: inline-block;
  padding: 12px;
  vertical-align: top;
}

.card-box-content>.el-card__body{
  padding: 0px;
  height: 140px;
}
.card-r-icon{
  width: 12px;
  height: 12px;
}


/* footer */


body {
    margin: 0;
    font-family: "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", "Helvetica Neue", Arial, sans-serif;
    font-size: 0.9375rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    text-align: left;
    background-color: #f5f7fb;
}

</style>