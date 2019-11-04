//更改用户状态信息
export const userStatus=(state,user)=>{
    if (user){
      state.currentUser = user
      state.isLogin = true
    }else if(user==null){
      //退出的时候清空sessionStorage里的东西
      sessionStorage.setItem('userName',null);
      sessionStorage.setItem('userToke','');
      state.currentUser = null;
      state.isLogin = false;
    }
  }
