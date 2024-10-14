<template>
	<view class="my-userinfo-container">

		<!-- Avatar and Nickname area -->
		<view class="top-box">
			<image :src="userinfo.avatarUrl" class="avatar"></image>
			<view class="nickname">{{userinfo.nickName}}</view>
		</view>

		<!-- Third panel -->
		<view class="panel">
			<view class="panel-list-item" @click="chooseAddress">
				<text>地址管理</text>
				<uni-icons type="arrowright" size="15"></uni-icons>
			</view>
			<view class="panel-list-item" @click="logout">
				<text>退出</text>
				<uni-icons type="arrowright" size="15"></uni-icons>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex'
	export default {
		name: "my-userinfo",
		data() {
			return {

			};
		},
		computed: {
			...mapState('m_user', ['userinfo']),
		},
		methods: {
			...mapMutations('m_user', ['updateUserInfo', 'updateToken', 'updateAddress']),
			async chooseAddress() {
				const [err, succ] = await uni.chooseAddress().catch(err => err)
				if (err === null && succ.errMsg === 'chooseAddress:ok') {
					this.updateAddress(succ)
				}
			},
			// Logout
			async logout() {
				// Ask the user whether to log out
				const [err, succ] = await uni.showModal({
					title: 'tips',
					content: 'Sure to log out？'
				}).catch(err => err)

				if (succ && succ.confirm) {
					// User confirmed the logout operation
					// Need to clear userinfo, token, and address in vuex
					this.updateUserInfo('')
					this.updateToken('')
					this.updateAddress({})
				}
			}
		}
	}
</script>

<style lang="scss">
	.my-userinfo-container {
		height: 100%;
		// Add a light gray background to the entire component structure
		background-color: #f4f4f4;

		.top-box {
			height: 400rpx;
			background-color: #fbb285;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;

			.avatar {
				display: block;
				width: 90px;
				height: 90px;
				border-radius: 45px;
				border: 2px solid white;
				box-shadow: 0 1px 5px black;
			}

			.nickname {
				color: white;
				font-weight: bold;
				font-size: 16px;
				margin-top: 10px;
			}
		}
	}

	.panel-list-item {
		height: 45px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 15px;
		padding: 0 10px;
	}
</style>

