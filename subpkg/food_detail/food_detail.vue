<template>
	<view>
		<!-- 图片展示 -->
		<swiper :indicator-dots="false" :autoplay="true" :interval="3000" :duration="1000">
			<swiper-item>
				<image :src="foodInfo.imgSrc" mode="widthFix" @click="preview()"></image>
			</swiper-item>
		</swiper>

		<!-- 菜品信息 -->
		<view class="food-info-box">
			<view class="name">
				{{foodInfo.name}}
			</view>
			<view class="price">
				¥ {{foodInfo.price}}
			</view>

			<view class="description">
				菜品描述：{{foodInfo.description}}
			</view>
		</view>


		<!-- 底部的导航 -->
<!-- 		<view>
			<van-submit-bar :price="currentShopTotalPrice" button-text="结算" bind:submit="onSubmit">
				<view style="display: flex;margin-right: auto;">
					<van-icon name="orders-o" :info="currentShopCartTotal" size="30px" />
				</view>
			</van-submit-bar>
		</view> -->
	</view>
</template>

<script>
	import { mapMutations } from 'vuex'
	import { mapState } from 'vuex'
	import { mapGetters } from 'vuex'
	export default {
		data() {
			return {
				foodId: 0,
				foodInfo: {},
				currentShopTotalPrice: 1567
			};
		},
		onLoad(opt) {
			const foodId = opt.foodId
			this.foodId = foodId
			this.getFoodById(foodId)

		},
		computed:{
			...mapGetters('m_cart', ['totalCart']),
			...mapState('m_cart', ['cart']),
			//购物车中,属于当前店铺的菜品数量
			currentShopCartTotal(){
				 const currentShopCart = this.cart.filter(x => x.shopId === this.foodInfo.shopId)
				 let c = 0
				 currentShopCart.forEach(food => c += food.count)
				 return c
				 
			},
		},
		methods: {
			...mapMutations('m_cart', ['addToCart']),
			async getFoodById(id) {
				let {
					data: res
				} = await uni.$http.get(`/food/food/${id}`)
				if (res.code === 200) {
					console.log("foodinfo", res.data)
					this.foodInfo = res.data
				}
			},
			preview() {

				uni.previewImage({

					urls: [this.foodInfo.imgSrc]
				})
			}
		}
	}
</script>

<style lang="scss">
	swiper {
		height: 400rpx;

		swiper-item {
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
		}
	}

	.food-info-box {
		padding: 10px;
		padding-right: 0;

		.name {
			font-weight: bold;
			font-size: 20px;
		}

		.price {
			color: #c00000;
			font-size: 18px;
			margin: 10px 0;
		}

		.description {
			margin-right: 20px;
		}
	}
</style>
