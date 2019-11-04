<template>
<div class="page">
  <div class="home-container">
    <div class="header h-top">
      <div class="d-flex">
          <div class="header-logo">
            <a href="./admin"><img src="../assets/tabler.svg" class="header-brand-img"></a>
          </div>
          <div class="write-header-tip">
            <div class="tip-l">写文章</div>
            <div class="tip-r">已保存草稿</div>
          </div>
          <div class="header-user">
            <div class="link-send">
              <el-dropdown trigger="click" placement="bottom" :hide-on-click="false">
                <a href="#" class="a-btn-asend">
                  <span class="el-dropdown-link">
                    发  布<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                </a>
                <el-dropdown-menu slot="dropdown"> 
                  <el-card class="box-card-send" shadow="never">
                    <div class="slot-header slot-tags">
                      <span class="span-tag-cag">标签</span>
                      <div class="text-item-send">
                      <el-tag
                        :key="tag"
                        v-for="tag in dynamicTags"
                        closable
                        :disable-transitions="false"
                        @close="handleClose(tag)">
                        {{tag}}
                      </el-tag>
                      <el-input
                        class="input-new-tag"
                        v-if="inputVisible"
                        v-model="inputValue"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm" 
                        >
                      </el-input>
                      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
                      </div> 
                    </div>
                    <div class="PublishPanel-content">
                        绑定合适的标签，能方便分类检索，文章也更容易让读者发现。 
                    </div>
                    <div class="slot-bottom">
                      <el-button class="a-btn-asend btn-send-last">发  布</el-button>
                    </div>
                  </el-card>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
            <div class="dropd-msg">
            <a href="#">
              <el-dropdown trigger="click" placement="bottom" >
                <span class="el-dropdown-link">
                  <svg-icon icon-class="more-horizontal" class="card-icon-more"/>
                </span>
                <el-dropdown-menu slot="dropdown" >
                  <el-dropdown-item  class="menu-item"><a href="#">草稿</a></el-dropdown-item>
                  <el-dropdown-item  class="menu-item">我的文章</el-dropdown-item>
                  <el-dropdown-item  class="menu-item">文章分类</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </a>
            </div>
          </div>
      </div>
    </div>
    <div class="WriteIndexLayout-main">
      <div class="WriteImage-wrapper">
          <div class="user-backgroundimage">
            <el-upload
            class="user-bj-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleImageSuccess"
            :on-remove="handleRemove"
            :before-upload="beforeImageUpload">
            <img v-if="bjimageUrl2" :src="bjimageUrl2" class="user-bj-image2">
            <i v-else class="el-icon-plus user-bj-uploader-icon"></i>
            </el-upload>
          </div>
      </div>
      <div class="WriteTitle-wrapper">
        <textarea rows="1" class="Input" placeholder="请输入标题（最多 50 个字）" style="height: 44px;"></textarea>
      </div>
      <div class="WritePost-wrapper">
        <div class="edit_container">
        <quill-editor 
            ref="myQuillEditor" 
            :options="editorOption" 
            @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
            @change="onEditorChange($event)">
        </quill-editor>
    </div>  
      </div>
    </div>
  </div>
  <!-- <blog-footer></blog-footer> -->
</div>
</template>

<script>
import blogHeader from '@/components/common/BlogHeader.vue'
import blogFooter from '@/components/common/BlogFooter.vue'
export default {
  name: 'admin',
  components: { blogHeader, blogFooter },  
  data(){
    return {
      //category
      // dynamicCategory: ['分类一', '分类二', '分类三'],
      // inputVisibleCag: false,
      // inputValueCag: '',
      //tag
      dynamicTags: ['标签一', '标签二', '标签三'],
      inputVisible: false,
      inputValue: '',
      // tag
      bjimageUrl2: 'https://pic2.zhimg.com/v2-ca671f4bd6ddf3d19f6b76a0188635e9.png',
      editorOption: {
        modules:{
          toolbar:[
            ['bold', 'italic'], 
            [{ 'header': 1 },'blockquote', 'code-block',{ 'list': 'ordered'}, { 'list': 'bullet' }],
            ['link','image','video'],
            ['clean'],
            ]
        }
      }
      }
  },computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
  },methods: {
      onEditorReady(editor) {}, // 准备编辑器
      onEditorBlur(){}, // 失去焦点事件
      onEditorFocus(){}, // 获得焦点事件
      onEditorChange(){}, // 内容改变事件
      saveHtml:function(event){
        alert(this.content);
      }
    } 
    ,methods:{
      openAdminHome(){
        this.$router.push({path: '/admin'});
      },
      handleImageSuccess(res, file) {
        this.bjimageUrl2 = URL.createObjectURL(file.raw);
      },
      handleRemove(res, file) {
        console.log(file);
      },
      beforeImageUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
        //tag
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        console.log(this.dynamicTags);
      },

      showInput() {
        // if(this.dynamicTags.size)
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      //category
      handleCloseCag(category) {
        this.dynamicCategory.splice(this.dynamicCategory.indexOf(category), 1);
      },

      showInputCag() {
        this.inputVisibleCag = true;
        this.$nextTick(_ => {
          this.$refs.saveCagInput.$refs.input.focus();
        });
      },
      handleInputConfirmCag() {
        let inputValueCag = this.inputValueCag;
        if (inputValueCag) {
          this.dynamicCategory.push(inputValueCag);
        }
        this.inputVisibleCag = false;
        this.inputValueCag = '';
      }
    }
}


</script>
<style rel="stylesheet/css" lang="css">
@import '../styles/css/blogheader.css';
@import '../styles/css/blogfooter.css';

.edit-header{
    position: fixed;
    top: 0;
    left: 0;
    background-color: #fff;
    z-index: 111;
    width: 100%;
    
}
.edit-header-c{
    height: 50px;
    background-color: #fff;
    width: 1000px;
    margin: 0 auto;
    border: 1px solid rgba(0, 40, 100, 0.12)
}
/* header */
.h-top{
    box-shadow: 0 1px 3px rgba(26,26,26,.1);
}

.card-icon-more{
    width: 24px;
    height: 24px;
}
.a-btn-asend{
    display:inline-block;
    font-size: 14px;
    width: 64px;
    text-align: center;
    border: 1px solid #0084ff;
    color: #0084ff;
    padding: 0.25rem 0.5rem;
    line-height: 1.33333333;
    border-radius: 3px;
    transition: 0.15s ease-in-out;
}
.a-btn-asend:hover{
    background-color: #ecf5ff;
    color: #0084ff;
}
.link-send{
  margin: 4px 20px 0px 0px;
}
.dropd-msg{
  margin-top: 6px;
}
.d-flex{
    width: 960px;
}
.write-header-tip{
    display: flex;
    font-size: 16px;
}
.tip-l{
    margin: 5px 8px;
    border-left: 1px solid #999;
    padding-left: 16px;
    font-weight: 600;
}
.tip-r{
    padding: 5px 8px;
    color: #999;
}
.card-icon-tag{
   width: 16px;
   height:16px;
   padding: 4px 0px 0px 4px;
}
.selete-tag{
    margin-right: 16px;
    padding-top: 5px;
}
.tag-text{
    display: inline-block;
}
.a-tag{
    color: #76839b;
}
.a-tag:hover{
    color: #000;
}
.menu-item{
    width: 140px;
}

.box-card-send{
  width: 300px;
  height: 320px;
  border: none;
}
/* main */
.WriteIndexLayout-main {
    margin: 47px auto 0;
    width: 660px;
}
.home-container{
    background-color: #fff;
}
/* image */
.UploadPicture-input {
    display: none;
}
.WriteImage-wrapper{
    background: #fff;
    color: grey;
    min-height: 192px;
    text-align: center;
}
.UploadPicture-wrapper{
    display: block;
    cursor: pointer;
}
.card-icon-camera{
    width: 36px;
    height: 32px;
}
.camera-tips{
    font-size: 16px;
    font-weight: 500;
}
.user-bj-image2{
  max-width: 660px;
}
/* title */
.WriteTitle-wrapper {
    margin: 16px 0;
    border: 0;
    padding: 0;
    height: auto;
    width: 100%;
    position: relative;
}
.WriteTitle-wrapper .Input{
    min-height: 44px;
    display: block;
    width: 100%;
    border: 0;
    font-size: 32px;
    line-height: 1.4;
    font-weight: 600;
    outline: none;
    box-shadow: none;
    font-family: inherit;
    color: #1a1a1a;
    padding: 0;
}
/* 编辑器 */
 .quill-editor {
  height: 350px;
  border-left: 0;
  border-right: 0;
 }
 /* .ql-formats{

 } */
.ql-container.ql-snow ,.ql-toolbar.ql-snow {
  border-left: 0;
  border-right: 0;
}
body{
    background-color: #fff;
}

/* tags */
  .el-tag {
    margin: 6px 10px;
  }
  .button-new-tag {
    margin: 6px 10px;
    margin-left: 6px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin: 4px 10px;
    vertical-align: bottom;
  }

  .text-item-send{
    height: 140px;
  }

  .btn-send-last{
    width: 80px;
    margin-top: 15px;
    margin-left: 10px;
  }
  .slot-bottom{
    margin-left: 10px;
    margin-right: 10px;
    border-top: 1px solid #ccc;
  }
  .slot-header{
    margin-left: 10px;
    margin-right: 10px;
  }
  .span-tag-cag{
    display:inline-block;
    font-family: "Microsoft YaHei";
    font-weight: 600;
    font-size: 14px;
  }

  .PublishPanel-content{
    max-height: 270px;
    overflow: auto;
    padding: 16px 16px;
    font-size: 14px;
    color: grey;
    line-height: 1.7;
  }
</style>
