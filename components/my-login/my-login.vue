<template>
  <view class="login-container">
    <!-- Icon prompting login -->
    <uni-icons type="contact-filled" size="100" color="#AFAFAF"></uni-icons>
    <!-- Login button -->
    <button type="primary" class="btn-login" @click="getUserInfoPro">WeChat Login</button>
    <!-- Login prompt -->
    <view class="tips-text">Login</view>
  </view>
</template>

<script>
import { mapMutations, mapState } from 'vuex'
export default {
  name: "my-login",
  data() {
    return {

    };
  },
  computed: {
    ...mapState('m_user', ['redirectInfo']),
  },
  methods: {
    ...mapMutations('m_user', ['updateUserInfo', 'updateToken', 'updateRedirectInfo']),
    // Get basic information of WeChat user
    getUserInfoPro(e) {
      uni.getUserProfile({
        desc: 'login',
        success: (res) => {
          // Store the user's basic information in vuex, all encrypted information
          console.log(res)
          this.updateUserInfo(res.userInfo)
          // Get the Token string after successful login
          this.getMyToken(res)
        },
        fail: (err) => {
          console.log(err)
        }
      })
    },
    async getMyToken(info) {
      // Call WeChat login API to get login code
      const [err, res] = await uni.login().catch(err => err)
      console.log(res, 'unilogin')
      // Determine if uni.login() call failed
      if (err || res.errMsg !== 'login:ok') return uni.$showError('uni.login failed！')

      // Prepare parameter object
      const query = {
        code: res.code,
        encryptedData: info.encryptedData,
        iv: info.iv,
        rawData: info.rawData,
        signature: info.signature
      }
      // Exchange for token
      const loginRes = await uni.$http.post('/login/wxLogin', query)
      console.log('Got the token', loginRes.data.data)
      if (loginRes.data.code !== 200) return uni.$showMsg('failed！')
      uni.$showMsg('success')
      console.log('Prepared login parameters', query)

      this.updateToken(loginRes.data.data)
      // Jump back to the original page
      console.log('Jump back to the original page, print redirect content', this.redirectInfo)
      this.navigateBack()
    },
    // Return to the page before login
    navigateBack() {
      // redirectInfo is not null, and navigation method is switchTab
      if (this.redirectInfo && this.redirectInfo.openType === 'switchTab') {
        // Use uni.switchTab() API provided by the mini program for page navigation
        uni.switchTab({
          // The page address to navigate to
          url: this.redirectInfo.from,
          // After successful navigation, reset redirectInfo object in vuex to null
          complete: () => {
            uni.$showMsg('success')
            this.updateRedirectInfo(null)
          }
        })
      } else if (this.redirectInfo && this.redirectInfo.openType === 'shopPage') {
        console.log('Here is returning to the shop checkout page')
        uni.navigateTo({
          url: this.redirectInfo.from,
        })
      }
    },
  }
}
</script>

<style lang="scss">
.login-container {
  // Styles for the login box
  height: 750rpx;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #f8f8f8;
  position: relative;
  overflow: hidden;

  // Draw a semi-elliptical shape at the bottom of the login box
  &::after {
    content: ' ';
    display: block;
    position: absolute;
    width: 100%;
    height: 40px;
    left: 0;
    bottom: 0;
    background-color: white;
    border-radius: 100%;
    transform: translateY(50%);
  }

  // Styles for the login button
  .btn-login {
    width: 90%;
    border-radius: 100px;
    margin: 15px 0;
    background-color: #fbb285;
  }

  // Styles for the prompt message below the button
  .tips-text {
    font-size: 12px;
    color: gray;
  }
}
</style>
