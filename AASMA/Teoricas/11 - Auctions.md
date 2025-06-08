# Auctions

### Real world auctions types

- English auction - Normal one, people bid, until no one contests the current price.
  - Every bidder knows the number of bidders in the auction.
  - The bids are public.
  - Bidders can submit several bids.
- Dutch Auction - Similar to normal one, but instead of going up the, it starts with a price and decreases until someone accepts it.
  - Every bidder knows the number of bidders in the auction.
  - The bid is public (and only one bid is submitted).
- First-Price - All bidders submit sealed bids simultaneously, no bidder knows the bids of
the other bidders, the highest bidder wins and pays the submitted price.
  - Every bidder knows the number of bidders in the auction.
  - The bids are private.
  - Bidders can only submit one bid.
- Second-Price - Similar to first-price, but the highest bidder wins and pays the second highest bid.
  - Every bidder knows the number of bidders in the auction.
  - The bids are private.
  - Bidders can only submit one bid.

## Bidding in First-Price Auctions

In a first-price sealed-bid auction with two risk-neutral bidders (agent 1 and agent 2), two valuations $v_1$ and $v_2$ (random probabilities). Hence agent 1 bids $\dfrac{1}{2}v_1$ and agent 2 bids $\dfrac{1}{2}v_2$ and $(\dfrac{1}{2}v_1, \dfrac{1}{2}v_2)$ is a Bayesian-Nash equilibrium.

If there are $N$ risk-neutral bidders and each bids $\dfrac{1}{2}v_i$ then the Bayesian-Nash equilibrium is $(\dfrac{N - 1}{N}v_1, \dfrac{N - 1}{N}v_2, \dots, \dfrac{N - 1}{N}v_N)$

## Bidding in Second-Price Auctions

In a second-price sealed-bid auction with $N$ risk-neutral bidders where each bids $v_i$, $(v_1, v_2, \dots, v_N)$ is a Nash equilibrium.

Considering the losers' strategies, reducing their bids does not change the profit because they still loose and do not pay anything. Increasing their bids above their valuation doesn't change their profit if they still loose with the increased bid. On the other hand, a loser increasing his bid to a winning price gives him the good, but at a price higher than the maximum he was willing to pay. Consequently, losers have no incentives to deviate from the NE.

Considering the winner's strategy, increasing his bid does not change anything and decreasing the bid can only hurt him. Consequently, the winner has no incentives to deviate from the NE.

## Revenue Equivalence

Definition $k^{th}$ order statistic of a distribution: the expected value of the $k^{th}$ largest of $n$ draws.

For $n$ draws from a uniform distribution $[0, v_{max}]$, the $k^{th}$ order statistic is:

$$\dfrac{n + 1 - k}{n + 1}v_{max}$$

If bidder $i$'s valuation $v_i$ is the highest, there are then $n - 1$ other valuations drawn from the uniform distribution on $[0, v_i]$. Hence, the expected value of the second-highest valuation (bid) is the first-order statistic of $n - 1$ draws from $[0, v_i]$:

$$\dfrac{(n - 1) + 1 - 1}{(n - 1) + 1}v_{max} = \dfrac{n - 1}{n}v_{max}$$

This provides a basis for our earlier claim about $n$-bidder first-price auctions.