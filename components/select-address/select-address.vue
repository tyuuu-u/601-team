<template>
	<view>
		<!-- Select delivery address -->
		<view class="address-choose-box" v-if="JSON.stringify(address) === '{}'">
			<button type="primary" size="mini" class="btnChooseAddress" @click="chooseAddress">Please select a delivery address</button>
		</view>

		<!-- Delivery information -->
		<!-- Click to reselect address -->
		<view class="address-info-box" v-else @click="chooseAddress">
			<view class="row1">
				<view class="row1-left">
					<view class="username">Recipient:<text>{{address.userName}}</text></view>
				</view>
				<view class="row1-right">
					<view class="phone">Phone:<text>{{address.telNumber}}</text></view>
					<uni-icons type="arrowright" size="16"></uni-icons>
				</view>
			</view>
			<view class="row2">
				<view class="row2-left">Delivery address:</view>
				<view class="row2-right">{{addstr}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { mapState, mapMutations, mapGetters } from 'vuex'
	export default {
		name: "select-address",
		data() {
			return {
				// address: {},
			};
		},
		computed: {
			...mapState('m_user', ['address']),
			...mapGetters('m_user', ['addstr']),
		},
		methods: {
			...mapMutations('m_user', ['updateAddress']),
			// Select delivery address
			async chooseAddress() {
				const [err, succ] = await uni.chooseAddress().catch(err => err)
				if (err === null && succ.errMsg === 'chooseAddress:ok') {
					this.updateAddress(succ)
				}
			},
		}
	}
</script>

<style lang="scss">
	// Select delivery address
	.address-choose-box {
		height: 90px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	// Delivery information
	.address-info-box {
		font-size: 15px;
		height: 90px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding: 0 5px;

		// First row
		.row1 {
			display: flex;
			justify-content: space-between;

			.row1-right {
				display: flex;
				align-items: center;

				.phone {
					margin-right: 5px;
				}
			}
		}

		// Second row
		.row2 {
			display: flex;
			align-items: center;
			margin-top: 10px;

			.row2-left {
				white-space: nowrap;
			}
		}
	}
</style>

