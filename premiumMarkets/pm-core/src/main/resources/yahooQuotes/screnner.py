from finvizfinance.screener.overview import Overview
overview = Overview()

# https://github.com/: finvizfinance/util.py
filters_dict = {'Beta': 'Over 2', 'Country': 'USA', 'Average Volume': 'Over 1M'}
#filters_dict = {'Beta': 'Over 2', 'Exchange': 'NASDAQ', 'Average Volume': 'Over 1M'}

overview.set_filter(filters_dict=filters_dict)
df = overview.screener_view(verbose=0)
print(df.to_csv())
