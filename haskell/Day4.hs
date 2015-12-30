import Data.ByteString.Lazy.Char8 (pack)
import Data.Digest.Pure.MD5
import Data.List

main = print res where
  res = find (isPrefixOf "000000" . digest) [1..]
  digest x = show . md5 . pack $ input ++ show x 
  input = "bgvyzdsv" 