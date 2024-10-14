<template>
	<view class="head-container">
		<view class="head-left">
			<view class="head-name">
				{{shopInfo.shopName}}
			</view>
			<view class="head-sum">
				Owner says: {{shopInfo.summary}}
			</view>
			<view class="head-phone">
				Phone: {{shopInfo.phone}}
			</view>
		</view>
		<view class="head-right">
			<image :src="shopInfo.shopSrc" style="height: 180rpx;width: 180rpx;border-radius:10rpx;"></image>
		</view>
	</view>
</template>

<script>
	export default {
		name:"shop-head",
		props:{
			shopInfo:{
				type:Object,
				default:{}
			}
		},
		data() {
			return {

			};
		},
		onLoad() {

		}

	}
</script>

<style lang="scss">
	.head-container{
		height: 90px;
		width: 750rpx;
		display: flex;
		padding-bottom: 10px;
		
		.head-left{
			width: 530rpx;
			padding-left: 20rpx;
			
			.head-name{
				padding: 5rpx 0;
				font-size: 20px;
				font-weight: bold;
			}
			.head-sum{
				padding: 5rpx 0;
				font-size: 13px;
				color: gray;
			}
			.head-phone{
				padding: 5rpx 0;
				font-size: 14px;
			}
		}
		
		.head-right{
			
		}
	}
</style>
