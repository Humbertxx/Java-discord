import yfinance as yf
import mplfinance as mpf


# ticker, interval, period are get from user
def main():
    tickers = ["U","AAPL","NVDA", "META"]
    data = ticker(tickers)
    
    plot_compare_ticker(data, tickers)

# get the stock, interval, and period of ticker
def ticker(tickers : str | list, interval : str = "1d", period : str = "1y"):
    if isinstance(tickers, str):
        tickers = [tickers]
    data = {}
    for ticker in tickers:
        df = yf.download(
                        ticker,
                        interval=interval,
                        auto_adjust=False,
                        period=period,
                        progress=False,
                        multi_level_index=False,
                        )
        df = df[['Open', 'High', 'Low', 'Close', 'Volume']]
        data[ticker] = df
    return data
    
# plotting to see performance of each stock. I can be adjusted also. 
def plot_compare_ticker(df, ticker_names : list, factor_size=None, volume=False):
    common_index = df[ticker_names[0]].index
    
    for t in ticker_names:
        common_index = common_index.intersection(df[t].index)
    
    base = ticker_names[0]
    base_df = df[base].loc[common_index].copy()
    
    if factor_size:
        base_factor = factor_size / base_df['Close'].iloc[0]
        base_df['Close'] *= base_factor

    addplots = []
    
    for t in ticker_names[1:]:
        df_t = df[t].loc[common_index]
        series = df_t['Close']
        
        if factor_size:
            factor = factor_size / df_t['Close'].iloc[0]
            series *= factor
       
        ap = mpf.make_addplot(series, panel=0, secondary_y=False, label=t)
        addplots.append(ap)

    fig, axes = mpf.plot(base_df, type="line", addplot=addplots, style='yahoo', volume=volume, title=f"{', '.join(ticker_names)} line Chart", 
                        ylabel = "Price ($US) Index (100 = first close)", xlabel = "Date", returnfig=True)
    
    ax = axes[0] if isinstance(axes, list) else axes
    ax.lines[0].set_label(base)
    ax.legend()
    
    mpf.show()
# TO DO: Portfolio logic store in Java
def plot_portfolio(df, show_type : str = "candle"):
    mpf.plot(df, type=show_type, style='yahoo', title=f' {show_type} Chart')
    
def plot_ticker(df, ticker_name : str, show_type : str = "candle"):
    mpf.plot(df, type=show_type, style='yahoo', title=f'{ticker_name} {show_type} Chart')
    
if __name__ == "__main__":
    main()